package com.ssafy.findme.service;


import java.util.List;

import com.ssafy.findme.domain.Users;

public interface IAccountService {
	List<Users> findAll();
	void save(Users users);
}
