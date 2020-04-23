package com.ssafy.findme.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.service.IAccountService;
import com.ssafy.findme.service.KakaoAPI;
import com.ssafy.findme.service.UserSha256;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class AccountController {

	@Autowired
	private IAccountService accountservice;
	@Autowired
	private KakaoAPI kakao;

	@GetMapping("/user/{email}/email-duplicate")
	@ApiOperation(value = "이메일 중복체크")
	public boolean emailduplicate(@PathVariable String email) {
		return !accountservice.emailDuplicateCheck(email);
	}

	@PostMapping("/user/signup")
	@ApiOperation(value = "가입하기")
	public void signup(@RequestBody UserDTO user) throws MessagingException {
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

	@PutMapping("/user/sendpassword")
	@ApiOperation(value = "임시 비밀번호 전송")
	public void sendpassword(@RequestParam String email) throws MessagingException {
		UserDTO user = accountservice.changePassword(email);
		accountservice.mailSend(user.getEmail(), user.getPassword(), user.getName());
		user.setPassword(UserSha256.encrypt(user.getPassword()));
		accountservice.updateProfile(user);
	}

	@PostMapping("/user/login")
	@ApiOperation(value = "일반계정 로그인")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password,
			HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			UserDTO user = new UserDTO();
			String encryPassword = UserSha256.encrypt(password);
			user.setEmail(email);
			user.setPassword(encryPassword);
			user = accountservice.login(user);
			String token = accountservice.getToken(user);
			res.setHeader("jwt-auth-token", token);

			resultMap.put("info", user);
			resultMap.put("token", token);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/user/find/{user_id}")
	@ApiOperation(value = "사용자 정보 조회")
	public UserDTO findById(@PathVariable Long user_id) {
		return accountservice.findById(user_id);
	}

	@GetMapping("/user/kakao_oauth")
	@ApiOperation(value = "카카오 계정으로 시작하기 및 로그인")
	public ResponseEntity<Map<String, Object>> kakaologin(@RequestParam("code") String code, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		String access_Token = kakao.getAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		String name = userInfo.get("nickname").toString();
		String email = userInfo.get("email").toString();
		String password = UserSha256.encrypt("kakao");

		try {
			UserDTO user = new UserDTO();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			// db에 이메일이 없으면 singup
			res.setHeader("jwt-auth-token", access_Token);

			if (accountservice.emailDuplicateCheck(email)) {
				if (accountservice.accountDuplicateCheck(email, password)) { // 로그인
					user = accountservice.info(email, password);
					resultMap.put("status", true);
					resultMap.put("info", user);
					status = HttpStatus.ACCEPTED;
				} else { // 일반계정을 카카오 계정으로 전환
					resultMap.put("status", false);
					resultMap.put("log", "카카오 계정으로 전환하시겠습니까? > 앞으로 카카오로만 로그인 가능 일반으로 로그인 불가");
					resultMap.put("info", user);
					status = HttpStatus.ACCEPTED;
					return new ResponseEntity<Map<String, Object>>(resultMap, status);
				}
			} else { // 카카오 계정으로 회원가입
				resultMap.put("status", false);
				resultMap.put("log", "회원가입이 필요합니다.");
				resultMap.put("info", user);
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<Map<String, Object>>(resultMap, status);
			}
		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/user/kakao_signup")
	@ApiOperation(value = "카카오 계정으로 가입하기 및 로그인")
	public ResponseEntity<Map<String, Object>> kakaosignup(@RequestBody UserDTO user, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			user.setAuthKey("Y");
			user.setPassword(UserSha256.encrypt("kakao"));
			user = accountservice.signUp(user);

			resultMap.put("status", true);
			resultMap.put("info", user);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/user/tokakao")
	@ApiOperation(value = "카카오 계정으로 전환")
	public ResponseEntity<Map<String, Object>> tokakao(@RequestParam String email, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			UserDTO user = accountservice.findbyEmail(email);
			user.setPassword(UserSha256.encrypt("kakao"));
			user = accountservice.updateProfile(user);

			resultMap.put("status", true);
			resultMap.put("info", user);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
