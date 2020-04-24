package com.ssafy.findme.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.findme.repository.ReviewRepository;
import com.ssafy.findme.repository.SurveyRepository;

public class TextMiningWork {
	@Autowired
	private ReviewRepository reviewrepo;
	
	@Autowired
	private SurveyRepository surveyrepo;
	
	public void textmining() {
		long language_id = 0;
		
		for (long i = 1; i <= 20; i++) {
			
		}
	}
}
