package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.findme.domain.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long>{
	List<Survey> findByLanguageId(Long language_id);
	
}
