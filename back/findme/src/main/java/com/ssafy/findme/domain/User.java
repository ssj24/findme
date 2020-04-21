package com.ssafy.findme.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity // 아래 변수들을 JPA를 사용해서 Entity로 설정
@Table(name = "user")
@Getter
@Setter
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false, unique = true)
	private String email;

//	@JsonIgnore
	@NotNull
	private String password;

	private String name;
	private String techStack;
	private String wishHope;
	private String wishJob;
	private boolean utility = true;
	private String authKey;

	@Enumerated(EnumType.STRING) // enum이름을 DB에 저장
	private RoleType roleType;

	// DB date 설정 어노테이션으로 datetime과의 차이점은 time_zone시스템 변수에 입력된 시간대 정보를 기반으로 데이터를
	// 입력받는다.
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;

	public enum RoleType {
		USER, ADMIN
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviewList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Symp> sympList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Unsymp> unsympList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Message> messageList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Survey> surveyList = new ArrayList<>();
	

	@OneToMany(mappedBy = "parent")
	@JsonIgnore
	private List<Similar> similarList = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Recommend> recommendList = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Pick> pickList = new ArrayList<>();
}
