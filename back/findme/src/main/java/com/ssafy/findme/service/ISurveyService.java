package com.ssafy.findme.service;

import com.ssafy.findme.dto.SurveyDTO;

public interface ISurveyService {
	void save(SurveyDTO surveydto);

	boolean findByConfirm(Long user_id, Long language_id);
}
