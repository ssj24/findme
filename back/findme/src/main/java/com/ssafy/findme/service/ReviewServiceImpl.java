package com.ssafy.findme.service;

import java.text.SimpleDateFormat;
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
import com.ssafy.findme.dto.UserDTO;
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

	@Override
	public void save(Long user_id, String content, Long language_id) {
		try {
			User user = accountrepo.findById(user_id);
			Review review = new Review();
			review.setContent(content);
			review.setUser(user);
			review.setName(user.getName());
			review.setCreatedAt(new Date());
			review.setUpdatedAt(new Date());
			review.setLanguageId(language_id);
			review.setSympCnt((long) 0);
			review.setUnsympCnt((long) 0);
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

//	@Override
//	public ReviewDTO findById(Long review_id) {
//		Review review = reviewrepo.findById(review_id).get();
//		ReviewDTO reviewDTO = entityMapper.convertToDomain(review, ReviewDTO.class);
//		return reviewDTO;
//	}

	public String transformDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	@Override
	public List<ReviewDTO> findAllByLanauageId(Long language_id, Long user_id) {
		List<ReviewDTO> reviewList = reviewrepo.findAllByLanguageId(language_id).stream()
				.map(e -> entityMapper.convertToDomain(e, ReviewDTO.class)).collect(Collectors.toList());

		// 날짜 형식 변경
		Date createdAt, updatedAt;
		for (int i = 0; i < reviewList.size(); i++) {
			ReviewDTO reviewDTO = reviewList.get(i);
			
			//작성자인지 확인
			if(reviewDTO.getUser().getId() == user_id) {
				reviewDTO.getUser().setCheck(true);
			}
			
			//review_id와 user_id 값으로 symp 테이블에 있는지 확인
			if(symprepo.findByReviewIdAndUserId(reviewDTO.getId(), user_id) != null) {
				reviewDTO.getUser().setCheckSymp(true);
			} else if(unsymprepo.findByReviewIdAndUserId(reviewDTO.getId(), user_id) != null) {
				reviewDTO.getUser().setCheckUnsymp(true);
			}
			createdAt = reviewDTO.getCreatedAt();
			updatedAt = reviewDTO.getUpdatedAt();
			reviewDTO.setTrans_createdAt(transformDate(createdAt));
			reviewDTO.setTrans_updatedAt(transformDate(updatedAt));
		}

		return reviewList;
	}

//	@Override
//	public List<ReviewDTO> findReviewByUserIdAndLanguageId(Long user_id, Long language_id) {
//		List<ReviewDTO> reviewList = reviewrepo.findReviewByUserIdAndLanguageId(user_id, language_id).stream()
//				.map(e -> entityMapper.convertToDomain(e, ReviewDTO.class)).collect(Collectors.toList());
//		
//		// 날짜 형식 변경
//		Date createdAt, updatedAt;
//		for (int i = 0; i < reviewList.size(); i++) {
//			ReviewDTO reviewDTO = reviewList.get(i);
//			createdAt = reviewDTO.getCreatedAt();
//			updatedAt = reviewDTO.getUpdatedAt();
//			reviewDTO.setTrans_createdAt(transformDate(createdAt));
//			reviewDTO.setTrans_updatedAt(transformDate(updatedAt));
//		}
//		return reviewList;
//	}

	
	// 리뷰 공감
//	@Override
//	public List<SympDTO> findAllSymp() {
//		return symprepo.findAll().stream().map(e -> entityMapper.convertToDomain(e, SympDTO.class))
//				.collect(Collectors.toList());
//	}

	@Override
	public boolean saveSymp(Long review_id, Long user_id) {
		try {
			//이미 공감을 눌렀으면 false
			Symp symp = symprepo.findByReviewIdAndUserId(review_id, user_id);
			if(symp != null)
				return false;
			
			//비공감에 저장되었으면 삭제하고 공감테이블에 저장
			Unsymp unsymp = unsymprepo.findByReviewIdAndUserId(review_id, user_id);
			if (unsymp != null) {
				deleteUnsymp(review_id, user_id);
			}
			
			Symp newsymp = new Symp();
			Review review = reviewrepo.findById(review_id).get();
			long symp_cnt = review.getSympCnt();
			review.setSympCnt(symp_cnt+1);
			reviewrepo.save(review);
			
			User user = accountrepo.findById(user_id);
			newsymp.setReview(review);
			newsymp.setUser(user);
			symprepo.save(newsymp);
			return true;
		} catch (Exception e) {
			System.out.println("ReviewServiceImpl saveSymp error");
		}
		return false;
	}

//	@Override
//	public List<SympDTO> findByLanguageIdAndUserId(Long language_id, Long user_id) {
//		return symprepo.findByLanguageIdAndUserId(language_id, user_id).stream().map(e -> entityMapper.convertToDomain(e, SympDTO.class))
//				.collect(Collectors.toList());
//	}

	@Override
	public void deleteSymp(Long review_id, Long user_id) {
		Symp symp = symprepo.findByReviewIdAndUserId(review_id, user_id);
		symprepo.delete(symp);
		Review review = reviewrepo.findById(review_id).get();
		long symp_cnt = review.getSympCnt();
		review.setSympCnt(symp_cnt - 1);
		reviewrepo.save(review);
	}

//	// 리뷰 비공감
//	@Override
//	public List<UnsympDTO> findAllUnsymp() {
//		return unsymprepo.findAll().stream().map(e -> entityMapper.convertToDomain(e, UnsympDTO.class))
//				.collect(Collectors.toList());
//	}

	@Override
	public boolean saveUnsymp(Long review_id, Long user_id) {
		try {
			Unsymp unsymp = unsymprepo.findByReviewIdAndUserId(review_id, user_id);
			if(unsymp != null)
				return false;
			
			Symp symp = symprepo.findByReviewIdAndUserId(review_id, user_id);
			if(symp != null) {
				deleteSymp(review_id, user_id);
			}
			
			Unsymp newunsymp = new Unsymp();
			Review review = reviewrepo.findById(review_id).get();
			long unsymp_cnt = review.getUnsympCnt();
			review.setUnsympCnt(unsymp_cnt + 1);
			reviewrepo.save(review);
			User user = accountrepo.findById(user_id);
			newunsymp.setReview(review);
			newunsymp.setUser(user);
			unsymprepo.save(newunsymp);
			return true;
		} catch (Exception e) {
			System.out.println("ReviewServiceImpl saveUnsymp error");
		}
		return false;
	}

//	@Override
//	public List<UnsympDTO> findUnsympByUserId(Long user_id) {
//		return unsymprepo.findByUserId(user_id).stream().map(e -> entityMapper.convertToDomain(e, UnsympDTO.class))
//				.collect(Collectors.toList());
//	}

	@Override
	public void deleteUnsymp(Long review_id, Long user_id) {
		Unsymp unsymp = unsymprepo.findByReviewIdAndUserId(review_id, user_id);
		unsymprepo.delete(unsymp);
		Review review = reviewrepo.findById(review_id).get();
		long unsymp_cnt = review.getUnsympCnt();
		review.setUnsympCnt(unsymp_cnt - 1);
		reviewrepo.save(review);
	}

	@Override
	public void countSympAndUnsymp(Long user_id) {
		
	}

}