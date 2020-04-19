package com.ssafy.findme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.service.ISurveyService;

@CrossOrigin(origins = ("*"))
@RequestMapping("/api")
@RestController
public class SurveyController {
	@Autowired
	private ISurveyService surveyserivce;
}
