package com.ssafy.findme.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.findme.domain.Reviews;
import com.ssafy.findme.domain.Symp;
import com.ssafy.findme.domain.Unsymp;

public interface IReviewService {
	void save(Reviews review);
	void delete(int review_seq);
	Optional<Reviews> findById(int review_seq);
	List<Reviews> findAll();
	void saveReviewSymp(Symp reviewSymp);
	void saveReviewUnsymp(Unsymp reviewUnsymp);
	Symp findOneSymp(int symp_seq);
	Unsymp findOneUnsymp(int unsymp_seq);
}
