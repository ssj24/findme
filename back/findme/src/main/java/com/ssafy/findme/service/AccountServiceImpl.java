package com.ssafy.findme.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.repository.AccountRepository;
@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private AccountRepository accoutrepository;
	
}
