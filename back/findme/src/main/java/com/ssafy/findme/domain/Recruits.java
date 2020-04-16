package com.ssafy.findme.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recruits")
@Getter
@Setter
public class Recruits {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comp_name;
	private String number;
	private String title;
	
	@Lob
	private String job_category;
	
	@Lob
	private String tech_stack;
	
	private String url;
	private boolean utility;
	
	private String post_date;
	private String start_date;
	private String due_date; //채용 시 마감인 경우도 있어서 String으로
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	@OneToMany(mappedBy = "recruit")
	@JsonIgnore
	private List<Message> message = new ArrayList<>();
}
