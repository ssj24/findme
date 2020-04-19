package com.ssafy.findme.service;

import java.util.Map;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.User;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.security.JwtService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountrepo;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private JwtService jwtService;

	@Value("${address.localhost.ipconfig}")
	private String IP;

	@Override
	public boolean emailDuplicateCheck(String email) {
		int check = accountrepo.countByEmail(email);
		if (check == 0)
			return true;
		else
			return false;
	}

	@Override
	public void signUp(User user) {
		accountrepo.save(user);
	}

	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	@Override
	// 회원가입 발송 이메일(인증키 발송)
	public void mailSendWithUserKey(String email, String name) throws MessagingException {
		// 인증을 위한 user_key 발급
		String key = getKey(false, 20);
		User user = accountrepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다"));
		user.setAuthKey(key);
		accountrepo.save(user);
		// 인증 메일 전송
		MimeMessage mail = mailSender.createMimeMessage();
		String subject = "[본인인증] FindMe 인증메일입니다.";
		String text = "<h2>안녕하세요 FindMe 입니다!</h2><br><br>" + "<h3>" + name + "님</h3>"
				+ "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " + "<a href=" + IP + "/user/key_alter?email=" + email + "&key="
				+ key + "'>인증하기</a></p>" + "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject(subject, "utf-8");
			mail.setText(text, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	// 인증 메일 확인
	public void alterUserKey(String email, String key) {
		User user = accountrepo.findByEmailAndAuthKey(email, key)
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인증메일입니다."));
		user.setAuthKey("Y");
		accountrepo.save(user);
	}

	@Override
	public String login(User trial) {
		User member = accountrepo.findByEmail(trial.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));
		if (!member.getAuthKey().equals("Y"))
			throw new IllegalArgumentException("인증되지 않은 계정입니다.");
		if (!UserSha256.encrypt(trial.getPassword()).equals(member.getPassword()))
			throw new IllegalArgumentException("잘못된 비밀번호입니다.");
		String token = jwtService.create(member.getName());
		return token;
	}

}
