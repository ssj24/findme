package com.ssafy.findme.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity //아래 변수들을 JPA를 사용해서 Entity로 설정
@Table(name="users")
@Data
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_seq;
	
	@Column(length = 100, nullable=false)
	private String user_email;
	@Column(length = 100, nullable = false)
	private String user_pwd;	
	@Column(length = 45, nullable = false)
	private String user_name;
	@Column(length = 2000, nullable = true)
	private String tech_stack;
	@Column(length = 2000, nullable = true)
	private String wish_hope;
	@Column(length = 2000, nullable = true)
	private String wish_job;
	@Column(nullable = false)
	private boolean user_utility;
	@Column(nullable = false)
	private Date created_at;
	
	@PrePersist // 생성일 자동 추가
	public void prePersist() {
		created_at = new Date();
	}
}
