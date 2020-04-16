package com.ssafy.findme.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewsDTO {
	private Long id;
	private String content;
	private Date created_at;
	private Date updated_at;
	private int symp_cnt;
	private int unsymp_cnt;
	private Long user_id;
}
