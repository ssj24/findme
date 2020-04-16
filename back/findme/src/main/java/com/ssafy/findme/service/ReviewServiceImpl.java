package com.ssafy.findme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Reviews;
import com.ssafy.findme.domain.Symp;
import com.ssafy.findme.domain.Unsymp;
import com.ssafy.findme.repository.ReviewRepository;
import com.ssafy.findme.repository.ReviewSympRepository;
import com.ssafy.findme.repository.ReviewUnsympRepository;
@Service
public class ReviewServiceImpl implements IReviewService {
	
	@Autowired
	private ReviewRepository reviewrepo;
	@Autowired
	private ReviewSympRepository reviewsymprepo;
	@Autowired
	private ReviewUnsympRepository reviewunsymprepo;

	@Override
	public void save(Reviews review) {
		reviewrepo.save(review);
	}

	@Override
	public void delete(int review_seq) {
		reviewrepo.deleteById(review_seq);
	}

	@Override
	public Optional<Reviews> findById(int review_seq) {
		return reviewrepo.findById(review_seq);
	}

	@Override
	public List<Reviews> findAll() {
		return reviewrepo.findAll();
	}

	@Override
	public void saveReviewSymp(Symp reviewSymp) {
		reviewsymprepo.save(reviewSymp);
	}

	@Override
	public void saveReviewUnsymp(Unsymp reviewUnsymp) {
		reviewunsymprepo.save(reviewUnsymp);
	}

	@Override
	public Symp findOneSymp(int symp_seq) {
		return null;
	}

	@Override
	public Unsymp findOneUnsymp(int unsymp_seq) {
		return reviewunsymprepo.findById(unsymp_seq).get();
	}

}
