package com.ssafy.findme.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.domain.User;
import com.ssafy.findme.service.IAccountService;
import com.ssafy.findme.service.UserSha256;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class AccountController {

	@Autowired
	private IAccountService accountservice;

	@GetMapping("/user/{email}/email-duplicate")
	@ApiOperation(value = "이메일 중복체크")
	public boolean emailduplicate(@PathVariable String email) {
		return accountservice.emailDuplicateCheck(email);
	}

	@PostMapping("/user/signup")
	@ApiOperation(value = "가입하기")
	public void signup(@RequestBody User user) throws MessagingException {
		// 비밀번호 암호화
		String encryPassword = UserSha256.encrypt(user.getPassword());
		user.setPassword(encryPassword);
		// 회원가입
		accountservice.signUp(user);
		// 인증메일
		accountservice.mailSendWithUserKey(user.getEmail(), user.getName());
	}

	@GetMapping("/user/key_alter")
	@ApiOperation(value = "이메일 인증하기")
	public void authentication(@RequestParam("email") String email, @RequestParam("key") String key) {
		accountservice.alterUserKey(email, key);
	}

	@PostMapping("/user/login")
	@ApiOperation(value = "로그인")
	public String login(@RequestParam String email, @RequestParam String password, HttpServletResponse res) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		String token = accountservice.login(user);
		res.setHeader("jwt-auth-token", token);
		return token;
	}
}
