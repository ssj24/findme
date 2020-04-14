package com.ssafy.findme.service;


import java.util.List;

import com.ssafy.findme.dto.Users;

public interface IAccountService {
	List<Users> findAll();
	void save(Users users);
}
