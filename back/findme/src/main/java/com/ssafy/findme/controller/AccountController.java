package com.ssafy.findme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.findme.service.IAccountService;

@CrossOrigin(origins = ("*"), maxAge = 6000) //혹시나 에러나면 maxAge 빼기
@RequestMapping("/api")

public class AccountController {

	@Autowired
	private IAccountService accountservice;
}
