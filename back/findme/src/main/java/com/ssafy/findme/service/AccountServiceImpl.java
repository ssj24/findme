package com.ssafy.findme.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.UserDTO;
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
	@Autowired
	private ModelMapper modelMapper;

	private String IP = "http://localhost:8888";

	@Override
	public boolean emailDuplicateCheck(String email) {
		int check = accountrepo.countByEmail(email);
		if (check == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean accountDuplicateCheck(String email, String password) {
		int check = accountrepo.countByEmailAndPassword(email, password);
		if (check == 0)
			return false;
		else
			return true;
	}

	@Override
	public UserDTO signUp(UserDTO user) {
		User member = modelMapper.map(user, User.class);
		CommandLineExecutor.execute("python src/main/python/similarAnalysis.py " + member.getId() + "");
		return modelMapper.map(accountrepo.save(member), UserDTO.class);
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
				+ "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " + "<a href= " + IP + "/api/user/key_alter?email=" + email
				+ "&key=" + key + ">인증하기</a></p>" + "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
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
	public UserDTO login(UserDTO trial) {
		User member = accountrepo.findByEmail(trial.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));
		if (!member.getAuthKey().equals("Y"))
			throw new IllegalArgumentException("인증되지 않은 계정입니다.");
		if (!trial.getPassword().equals(member.getPassword()))
			throw new IllegalArgumentException("잘못된 비밀번호입니다. 또는 카카오계정으로 시도해보세요.");
		return modelMapper.map(member, UserDTO.class);

	}

	@Override
	public String getToken(UserDTO user) {
		String token = jwtService.create(modelMapper.map(user, User.class));
		return token;
	}

	@Override
	public UserDTO info(String email, String password) {
		return modelMapper.map(accountrepo.findByEmailAndPassword(email, password), UserDTO.class);
	}

	@Override
	public UserDTO changePassword(String email) {
		User user = accountrepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다"));
		String randomPassword = getRamdomPassword(10);
		user.setPassword(randomPassword);
		return modelMapper.map(user, UserDTO.class);
	}

	private static String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	// 임시비밀번호 발송
	@Override
	public void mailSend(String email, String password, String name) throws MessagingException {
		MimeMessage mail = mailSender.createMimeMessage();
		String subject = "[임시비밀번호 발급] FindMe 임시 비밀번호 발급 메일입니다.";
		String text = "<h2>안녕하세요 FindMe 입니다!</h2><br><br>" + "<h3>" + name + "님</h3>" + "<p>임시비밀번호는 </p><br><h4>"
				+ password + "</h4><br><p>입니다.</p>" + "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
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
	public UserDTO updateProfile(UserDTO user) {
		return modelMapper.map(accountrepo.save(modelMapper.map(user, User.class)), UserDTO.class);
	}

	@Override
	public UserDTO findById(Long user_id) {
		String result = execute("python src/main/python/similarAnalysis.py " + user_id + "");
		System.out.println(result);
		if(result.equals("dismatch")) {
			//유사도 검사해서 80%이하면 맞춤공고 -> recommend 테이블에 넣기
		}
		return modelMapper.map(accountrepo.findById(user_id), UserDTO.class);
	}

	public static String execute(String cmd) {
		Process process = null;
		Runtime runtime = Runtime.getRuntime();
		StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
		StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼
		BufferedReader successBufferReader = null; // 성공 버퍼
		BufferedReader errorBufferReader = null; // 오류 버퍼
		String msg = null; // 메시지

		List<String> cmdList = new ArrayList<String>();

		// 운영체제 구분 (window, window 가 아니면 무조건 linux 로 판단)
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			cmdList.add("cmd");
			cmdList.add("/c");
		} else {
			cmdList.add("/bin/sh");
			cmdList.add("-c");
		}
		// 명령어 셋팅
		cmdList.add(cmd);
		String[] array = cmdList.toArray(new String[cmdList.size()]);

		try {

			// 명령어 실행
			process = runtime.exec(array);

			// shell 실행이 정상 동작했을 경우
			successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));

			while ((msg = successBufferReader.readLine()) != null) {
				successOutput.append(msg + System.getProperty("line.separator"));
			}

			// shell 실행시 에러가 발생했을 경우
			errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
			while ((msg = errorBufferReader.readLine()) != null) {
				errorOutput.append(msg + System.getProperty("line.separator"));
			}

			// 프로세스의 수행이 끝날때까지 대기
			process.waitFor();

			// shell 실행이 정상 종료되었을 경우
			if (process.exitValue() == 0) {
				System.out.println("성공");
				//윈도우 개행문자들을 모두 지워버린다.
				return successOutput.toString().replace(System.getProperty( "line.separator" ), "");
			} else {
				// shell 실행이 비정상 종료되었을 경우
				System.out.println("비정상 종료");
				return errorOutput.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				process.destroy();
				if (successBufferReader != null)
					successBufferReader.close();
				if (errorBufferReader != null)
					errorBufferReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}
