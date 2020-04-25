package com.ssafy.findme.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Review;
import com.ssafy.findme.domain.Symp;
import com.ssafy.findme.domain.Unsymp;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.ReviewDTO;
import com.ssafy.findme.dto.SympDTO;
import com.ssafy.findme.dto.UnsympDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.repository.ReviewRepository;
import com.ssafy.findme.repository.SympRepository;
import com.ssafy.findme.repository.UnsympRepository;

@Service
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	private ReviewRepository reviewrepo;
	@Autowired
	private SympRepository symprepo;
	@Autowired
	private UnsympRepository unsymprepo;
	@Autowired
	private AccountRepository accountrepo;

	@Autowired
	private EntityMapper entityMapper;

	public boolean checkUserId(Long user_id) {
		// 존재하는 유저 아이디라면 true를
		// 존재하지 않는 유저 아이디라면 false를 return
		return false;
	}

	@Override
	public void save(Long user_id, String content, Long language_id) {
		try {
			User user = accountrepo.findById(user_id);
			Review review = new Review();
			review.setContent(content);
			review.setUser(user);
			review.setCreatedAt(new Date());
			review.setUpdatedAt(new Date());
			review.setLanguageId(language_id);
			review.setSympCnt((long)0);
			review.setUnsympCnt((long)0);
			reviewrepo.save(review);
		} catch (Exception e) {
			System.out.println("ReviewServiceImpl save error");
		}
	}

	@Override
	public void update(Long review_id, String content) {
		try {
			if (content != "" && content != null) {
				Review review = reviewrepo.findById(review_id).get();
				review.setContent(content);
				review.setUpdatedAt(new Date());
				reviewrepo.save(review);
			} else {
				System.out.println("ReviewServiceImpl update fail!! No content");
			}
		} catch (Exception e) {
			System.out.println("ReviewService update error");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long review_id) {
		Review reivew = reviewrepo.findById(review_id).get();
		reviewrepo.delete(reivew);
	}

	@Override
	public ReviewDTO findById(Long review_id) {
		Review review = reviewrepo.findById(review_id).get();
		ReviewDTO reviewDTO = entityMapper.convertToDomain(review, ReviewDTO.class);
		return reviewDTO;
	}
	
	@Override
	public List<ReviewDTO> findAllByLanauageId(Long language_id) {
		return reviewrepo.findAllByLanguageId(language_id).stream().map(e -> entityMapper.convertToDomain(e, ReviewDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ReviewDTO> findReviewByUserIdAndLanguageId(Long user_id, Long language_id) {
		return reviewrepo.findReviewByUserIdAndLanguageId(user_id, language_id).stream().map(e -> entityMapper.convertToDomain(e, ReviewDTO.class))
				.collect(Collectors.toList());
	}

// 리뷰 공감
	@Override
	public List<SympDTO> findAllSymp() {
		return symprepo.findAll().stream().map(e -> entityMapper.convertToDomain(e, SympDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void saveSymp(Long review_id, Long user_id) {
		try {
			Symp symp = new Symp();
			Review review = reviewrepo.findById(review_id).get();
			Long symp_cnt = review.getSympCnt();
			review.setSympCnt(symp_cnt + 1);
			User user = accountrepo.findById(user_id);
			symp.setReview(review);
			symp.setUser(user);
			symprepo.save(symp);
		} catch (Exception e) {
			System.out.println("ReviewServiceImpl saveSymp error");
		}
	}

	@Override
	public List<SympDTO> findSympByUserId(Long user_id) {
		return symprepo.findByUser_Id(user_id).stream().map(e -> entityMapper.convertToDomain(e, SympDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteSymp(Long review_id, Long user_id) {
		Symp symp = symprepo.findByReview_IdAndUser_Id(review_id, user_id);
		symprepo.delete(symp);
	}

// 리뷰 비공감
	@Override
	public List<UnsympDTO> findAllUnsymp() {
		return unsymprepo.findAll().stream().map(e -> entityMapper.convertToDomain(e, UnsympDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void saveUnsymp(Long review_id, Long user_id) {
		try {
			Unsymp unsymp = new Unsymp();
			Review review = reviewrepo.findById(review_id).get();
			Long unsymp_cnt = review.getSympCnt();
			review.setSympCnt(unsymp_cnt - 1);
			User user = accountrepo.findById(user_id);
			unsymp.setReview(review);
			unsymp.setUser(user);
			unsymprepo.save(unsymp);
		} catch (Exception e) {
			System.out.println("ReviewServiceImpl saveUnsymp error");
		}
	}

	@Override
	public List<UnsympDTO> findUnsympByUserId(Long user_id) {
		return unsymprepo.findByUser_Id(user_id).stream().map(e -> entityMapper.convertToDomain(e, UnsympDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteUnsymp(Long review_id, Long user_id) {
		Unsymp unsymp = unsymprepo.findByReview_IdAndUser_Id(review_id, user_id);
		unsymprepo.delete(unsymp);
	}


}