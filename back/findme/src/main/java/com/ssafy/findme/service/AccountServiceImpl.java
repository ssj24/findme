package com.ssafy.findme.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Users;
import com.ssafy.findme.repository.AccountRepository;
@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private AccountRepository accoutrepo;

	@Override
	public List<Users> findAll() {
		return accoutrepo.findAll();
	}

	@Override
	public void save(Users users) {
		accoutrepo.save(users);
	}
}
