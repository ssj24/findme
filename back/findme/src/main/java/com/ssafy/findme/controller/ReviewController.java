//package com.ssafy.findme.controller;
//
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.hibernate.annotations.Parent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.findme.dto.ReviewSymp;
//import com.ssafy.findme.dto.ReviewUnsymp;
//import com.ssafy.findme.dto.Reviews;
//import com.ssafy.findme.service.IReviewService;
//
//import io.swagger.annotations.ApiOperation;
//import lombok.Builder;
//
//@CrossOrigin(origins = ("*"))
//@RequestMapping("/api")
//@RestController
//public class ReviewController {
//	@Autowired 
//	private IReviewService reviewservice;
//	
//	//작성, 업데이트 시간이 이상함
//	@PostMapping("/review/write/{user_seq}")
//	@ApiOperation(value = "리뷰 작성")
//	public void save(@RequestBody Reviews review, @PathVariable int user_seq) {
//		try {
//			review.setUser_seq(user_seq);
//			Reviews reviews = Reviews.builder().review_content(review.getReview_content()).user_seq(user_seq).build();
//			reviewservice.save(reviews);
//		} catch (Exception e) {
//			System.out.println("review/write error");
//		}
//	}
//	
//	@PutMapping("/review/update/{review_seq}")
//	@ApiOperation(value = "리뷰 업데이트")
//	@ResponseBody
//	public void update(@RequestBody String content, @PathVariable int review_seq) {
//		try {
//			Optional<Reviews> pre_review = reviewservice.findById(review_seq);
//			pre_review.get().setReview_content(content);
//			pre_review.get().setUpdated_at(new Date());
//			reviewservice.save(pre_review.get());
//		} catch (Exception e) {
//			System.out.println("review/update error");
//		}
//	}
//	
//	@GetMapping("/review/findAll")
//	@ApiOperation(value = "전체 리뷰 리스트")
//	@ResponseBody
//	public List<Reviews> findAll() {
//		return reviewservice.findAll();
//	}
//	
//	@PostMapping("/review/symp/{user_seq}")
//	@ApiOperation(value = "리뷰 공감")
//	@ResponseBody
//	public int reviesw_symp(@RequestParam int review_seq, @PathVariable int user_seq) {
//		try {
//			//이미 공감 또는 비공감을 누른 사람이면?
//			if(reviewservice.findOneSymp(symp_seq))
//			Reviews review = reviewservice.findById(review_seq).get();
//			int symp_cnt = review.getSymp_cnt();
//			review.setSymp_cnt(symp_cnt + 1);
//			reviewservice.save(review);
//			
//			ReviewSymp review_symp = ReviewSymp.builder().user_seq(user_seq).review_seq(review_seq).build();
//			reviewservice.saveReviewSymp(review_symp);
//			return review.getSymp_cnt();
//		} catch (Exception e) {
//			System.out.println("review/symp error");
//		}
//		return -1;
//	}
//	
//	@PostMapping("/review/unsymp/{user_seq}")
//	@ApiOperation(value = "리뷰 비공감")
//	@ResponseBody
//	public int reviesw_unsymp(@RequestParam int review_seq, @PathVariable int user_seq) {
//		try {
//			ReviewUnsymp reviesw_unsymp = ReviewUnsymp.builder().user_seq(user_seq).review_seq(review_seq).build();
//			reviewservice.saveReviewUnsymp(reviesw_unsymp);
//			Reviews review = reviewservice.findById(review_seq).get();
//			int unsymp_cnt = review.getUnsymp_cnt();
//			review.setUnsymp_cnt(unsymp_cnt + 1);
//			reviewservice.save(review);
//			return review.getUnsymp_cnt();
//		} catch (Exception e) {
//			System.out.println("review/unsymp error");
//		}
//		return -1;
//	}
//	
//}
