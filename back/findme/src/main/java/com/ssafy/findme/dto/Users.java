package com.ssafy.findme.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PUBLIC) //기본 생성자 자동 추가
@Entity //아래 변수들을 JPA를 사용해서 Entity로 설정
@Table(name="users")
//@Data
@Getter
@Setter
@ToString
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private boolean user_utility = true;
	@Column(nullable = false)
	private Date created_at = new Date();
	
	//호출되기 전에 값을 설정해두는 메서드인데 위에서 직접 값을 넣는거랑 뭐가 다른지 모르겠음
//	@PrePersist
//	public void prePersist() {
//		this.created_at = new Date();
//	}
	
	@Builder
	public Users(String user_email, String user_pwd, String user_name) {
		this.user_email = user_email;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
	}
}
