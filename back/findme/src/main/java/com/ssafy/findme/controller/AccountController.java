package com.ssafy.findme.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.Users;
import com.ssafy.findme.service.IAccountService;
import com.ssafy.findme.service.UserMailSendService;
import com.ssafy.findme.service.UserSha256;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class AccountController {

	@Autowired
	private IAccountService accountservice;
	@Autowired
	private UserMailSendService mailsender;

//	@GetMapping("/users")
//	@ApiOperation(value = "전체사용자조회")
//	@ResponseBody
//	public List<Users> list() {
//		try {
//			List<Users> memberList = accountservice.findAll();
//			return memberList;
//		} catch (DataIntegrityViolationException e) {
//			System.out.println("history already exist");
//		}
//		return null;
//	}
//
//	@PostMapping("/insert")
//	@ApiOperation(value = "사용자추가")
//	public void insert(@RequestBody Users user) {
//		try {
////			Users users = Users.builder().user_email(user.getUser_email()).user_pwd(user.getUser_pwd())
////					.user_name(user.getUser_name()).build();
//			accountservice.save(user);
//		} catch (DataIntegrityViolationException e) {
//			System.out.println("insert fail");
//		}
//	}

	@PostMapping("/user/signup")
	@ApiOperation(value = "가입하기")
	public void signup(@RequestBody Users user) throws MessagingException {
		// 비밀번호 암호화
		String encryPassword = UserSha256.encrypt(user.getUser_pwd());
		user.setUser_pwd(encryPassword);
		// 회원가입
		accountservice.signUp(user);
		// 인증메일
		accountservice.mailSendWithUserKey(user.getEmail(), user.getName());

	}
	
	@GetMapping("/user/key_alter")
	@ApiOperation(value = "이메일 인증하기")
	public void authentication(@RequestParam("user_email") String email, @RequestParam("user_key") String key) {

		accountservice.alterUserKey(email, key);

	}
}
