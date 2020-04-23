package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.findme.domain.Unsymp;

public interface UnsympRepository extends JpaRepository<Unsymp, Long> {
	List<Unsymp> findByUser_Id(Long user_id);
	Unsymp findByReview_IdAndUser_Id(Long review_id, Long user_id);
}
