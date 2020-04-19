package com.ssafy.findme.dto;

import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import lombok.Getter;

@Getter
@Setter
@ToString
public class SurveyDTO {
	private Long id;
	private int useReason;
	private int advantage;
	private int disadvantage;
	private String totalReview;
	private Date created_at;
	private Long userId;
}
