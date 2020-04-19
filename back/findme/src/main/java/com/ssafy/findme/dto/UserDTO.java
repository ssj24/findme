package com.ssafy.findme.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String eamil;
	private String password;
	private String name;
	private String tech_stack;
	private String wish_hope;
	private String wish_job;
	private boolean utility;
	private String auth_key;
	private RoleType roleType;
	private Date created_at;
	public enum RoleType {
		USER, ADMIN
	}
}
