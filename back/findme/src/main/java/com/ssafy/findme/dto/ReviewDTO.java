package com.ssafy.findme.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
	private Long id;
	private String content;
	private Date createdAt;
	private Date updatedAt;
	private Long sympCnt;
	private Long unsympCnt;
	private Long userId;
	private Long languageId;
}
