package com.ssafy.findme.service;

import javax.mail.MessagingException;

import com.ssafy.findme.domain.Users;

public interface IAccountService {
//	List<Users> findAll();
	void signUp(Users user);

	void mailSendWithUserKey(String email, String name) throws MessagingException;

	void alterUserKey(String email, String key);
}
