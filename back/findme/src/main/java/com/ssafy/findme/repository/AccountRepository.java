package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.dto.Users;

@Repository
public interface AccountRepository extends JpaRepository<Users, Integer>{

}
