package com.ssafy.findme.service;

import javax.mail.MessagingException;

import com.ssafy.findme.domain.User;

public interface IAccountService {
	User signUp(User user);

	void mailSendWithUserKey(String email, String name) throws MessagingException;

	void alterUserKey(String email, String key);

	boolean emailDuplicateCheck(String email);

	boolean accountDuplicateCheck(String email, String password);

	User login(User user);

	String getToken(User user);

//	User info(long id);

	User info(String email, String password);

	User changePassword(String email);

	void mailSend(String email, String password, String name) throws MessagingException;

	void updateProfile(User user);
}
