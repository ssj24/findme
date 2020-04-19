package com.ssafy.findme.service;

import javax.mail.MessagingException;

import com.ssafy.findme.domain.User;

public interface IAccountService {
	void signUp(User user);

	void mailSendWithUserKey(String email, String name) throws MessagingException;

	void alterUserKey(String email, String key);

	boolean emailDuplicateCheck(String email);

	String login(User user);

}
