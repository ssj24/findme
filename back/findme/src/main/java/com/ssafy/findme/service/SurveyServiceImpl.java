package com.ssafy.findme.service;

import java.util.Date;

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
	private AccountRepository accountrepo;
	
	@Override
	public void save(SurveyDTO surveydto) {
		try {
			Survey survey = new Survey();
			User user = accountrepo.findById(surveydto.getUserId());
			user.setId(surveydto.getUserId());
			survey.setCreatedAt(new Date());
			survey.setUser(user);
			survey.setAdvantage(surveydto.getAdvantage());
			survey.setDisadvantage(surveydto.getDisadvantage());
			survey.setTotalReview(surveydto.getTotalReview());
			survey.setUseReason(surveydto.getUseReason());
			survey.setLanguageId(surveydto.getLanguageId());
			surveyrepo.save(survey);
		} catch (Exception e) {
			System.out.println("SurveyService save error");
		}
	}

	@Override
	public boolean findByConfirm(Long user_id, Long language_id) {
		Survey survey = surveyrepo.findByUserIdAndLanguageId(user_id, language_id);
		if(survey != null)
			return true;
		else
			return false;
	}

}
