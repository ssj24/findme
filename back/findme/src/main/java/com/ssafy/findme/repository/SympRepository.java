package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Symp;

@Repository
public interface SympRepository extends JpaRepository<Symp, Long>{
	List<Symp> findByUser_Id(Long user_id);
	Symp findByReview_IdAndUser_Id(Long review_id, Long user_id);
}
