package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Recruit;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
	public Recruit findByNumber(String number);
}
