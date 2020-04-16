package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Users;

@Repository
public interface AccountRepository extends JpaRepository<Users, Integer> {

	public List<Users> findByEmail(String email);

	public List<Users> findByEmailAndKey(String email, String key);

	public List<Users> findByNameLike(String keyword);
}
