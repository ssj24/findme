package com.ssafy.findme.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.service.IUserService;
import com.ssafy.findme.service.UserSha256;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class UserController {

	@Autowired
	private IUserService userservice;

	@GetMapping("/user/{id}/profile")
	@ApiOperation(value = "프로필")
	public UserDTO profile(@PathVariable Long id, HttpServletRequest req) {
		UserDTO user = userservice.findById(id);
		return user;
	}

	@PutMapping("/user/{id}/updateprofile")
	@ApiOperation(value = "프로필 수정")
	public ResponseEntity<Map<String, Object>> updateprofile(@PathVariable Long id, @RequestParam String techStack,
			@RequestParam String wishHope, @RequestParam String wishJob, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			UserDTO user = userservice.findById(id);
			user.setTechStack(techStack);
			user.setWishHope(wishHope);
			user.setWishJob(wishJob);
			UserDTO member = userservice.updateProfile(user);

			resultMap.put("status", true);
			resultMap.put("info", member);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/user/{id}/updatepassword")
	@ApiOperation(value = "비밀번호 변경")
	public ResponseEntity<Map<String, Object>> updatepassword(@PathVariable Long id, @RequestParam String password,
			HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			UserDTO user = userservice.findById(id);
			if (user.getPassword().equals(UserSha256.encrypt("kakao"))) {
				resultMap.put("status", false);
				resultMap.put("log", "카카오 계정이므로 비밀번호 변경이 불가능합니다.");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<Map<String, Object>>(resultMap, status);
			}
			String encryPassword = UserSha256.encrypt(password);
			user.setPassword(encryPassword);
			UserDTO member = userservice.updateProfile(user);

			// logout해야할 것 같은데

			resultMap.put("status", true);
			resultMap.put("info", member);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
