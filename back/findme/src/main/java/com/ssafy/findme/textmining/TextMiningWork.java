package com.ssafy.findme.textmining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.findme.domain.Review;
import com.ssafy.findme.domain.Survey;
import com.ssafy.findme.repository.ReviewRepository;
import com.ssafy.findme.repository.SurveyRepository;

public class TextMiningWork {
	@Autowired
	private ReviewRepository reviewrepo;
	
	@Autowired
	private SurveyRepository surveyrepo;
	
//	public void textmining() {
//		long language_id = 0;
//		
//		for (long i = 1; i <= 20; i++) {
//			List<Review> reviewAllList = reviewrepo.findByLanguage_Id(language_id+i);
//			List<Survey> surveyAllList = surveyrepo.findByLanguage_Id(language_id+i);
//			
//			
//		}
//	}
}
