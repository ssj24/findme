package com.ssafy.findme.service;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Survey;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.SurveyDTO;
import com.ssafy.findme.repository.SurveyRepository;

@Service
public class SurveyService implements ISurveyService {
	@Autowired
	private SurveyRepository surveyrepo;
	
	@Autowired
	private EntityManager entityMapper;

	@Override
	public void saveSurvey(SurveyDTO surveydto) {
		try {
			User user = new User(); //user 메서드 생성디면 바꾸기
			Survey survey = new Survey();
			user.setId(surveydto.getUserId());
			survey.setCreated_at(new Date());
			survey.setUser(user);
			survey.setAdvantage(surveydto.getAdvantage());
			survey.setDisadvantage(surveydto.getDisadvantage());
			survey.setTotal_review(surveydto.getTotalReview());
			survey.setUse_reason(surveydto.getUseReason());
			surveyrepo.save(survey);
		} catch (Exception e) {
			System.out.println("SurveyService saveSurvey error");
		}
	}

}
