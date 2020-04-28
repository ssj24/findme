package com.ssafy.findme.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecruitDTO {
	private Long id;
	private String compName;
	private String title;
	private String jobCategory;
	private String tech_stack;
	private String url;
	private boolean utility;
	private String number;
	private String postDate;
	private String startDate;
	private String dueDate;
}
