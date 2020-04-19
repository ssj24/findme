package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.findme.domain.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long>{
	
}
