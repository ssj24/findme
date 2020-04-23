package com.ssafy.findme.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Survey;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.SurveyDTO;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.repository.SurveyRepository;

@Service
public class SurveyServiceImpl implements ISurveyService {
	@Autowired
	private SurveyRepository surveyrepo;

	@Autowired
	private EntityManager entityMapper;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountRepository accountrepo;
	
	@Override
	public void save(SurveyDTO surveydto) {
		try {
			Survey survey = modelMapper.map(surveydto, Survey.class);
//			User user = accountrepo.findById(surveydto.getUserId());
//			user.setId(surveydto.getUserId());
//			survey.setCreated_at(new Date());
//			survey.setUser(user);
//			survey.setAdvantage(surveydto.getAdvantage());
//			survey.setDisadvantage(surveydto.getDisadvantage());
//			survey.setTotal_review(surveydto.getTotalReview());
//			survey.setUse_reason(surveydto.getUseReason());
			surveyrepo.save(survey);
		} catch (Exception e) {
			System.out.println("SurveyService saveSurvey error");
		}
	}

}
