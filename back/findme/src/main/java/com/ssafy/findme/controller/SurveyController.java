package com.ssafy.findme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.SurveyDTO;
import com.ssafy.findme.service.ISurveyService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RequestMapping("/api")
@RestController
public class SurveyController {
	@Autowired
	private ISurveyService surveyserivce;
	
	@GetMapping("/survey/write")
	@ApiOperation(value = "설문조사 작성")
	public void write(@RequestBody SurveyDTO surveydto) {
		surveyserivce.save(surveydto);
	}
}
