package com.ssafy.findme.repository;


import org.springframework.data.repository.CrudRepository;

import com.ssafy.findme.dto.Users;

public interface AccountRepository extends CrudRepository<Users, Integer>{
// <T, TD extends Serializable>
//	Iterable<Users> findAll();
}
