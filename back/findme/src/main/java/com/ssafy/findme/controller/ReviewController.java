package com.ssafy.findme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.ReviewDTO;
import com.ssafy.findme.dto.SympDTO;
import com.ssafy.findme.dto.UnsympDTO;
import com.ssafy.findme.service.IReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RequestMapping("/api")
@RestController
public class ReviewController {
	@Autowired
	private IReviewService reviewservice;

	// 작성, 업데이트 시간이 이상함
	@PostMapping("/review/write")
	@ApiOperation(value = "리뷰 작성")
	public void save(@RequestParam Long user_id, @RequestParam String content, @RequestParam Long language_id) {
		reviewservice.save(user_id, content, language_id);
	}

	@PutMapping("/review/update/{review_id}")
	@ApiOperation(value = "리뷰 업데이트")
	@ResponseBody
	public void update(@PathVariable Long review_id, @RequestParam String content) {
		reviewservice.update(review_id, content);
	}

	@GetMapping("/review/findAll/{language_id}")
	@ApiOperation(value = "프로그래밍 언어별 전체 리뷰 조회")
	@ResponseBody
	public List<ReviewDTO> findAllByLanguageId(@PathVariable Long language_id, @RequestParam Long user_id) {
		return reviewservice.findAllByLanauageId(language_id,user_id);
	}

	@DeleteMapping("/review/delete/{review_id}")
	@ApiOperation(value = "리뷰 삭제")
	public void delete(@PathVariable Long review_id) {
		reviewservice.delete(review_id);
	}

//	@GetMapping("/review/findAll/{language_id}/user/{user_id}")
//	@ApiOperation(value = "사용자별 작성 리뷰 리스트")
//	public List<ReviewDTO> findReviewByUserIdAndLanguageId(@PathVariable Long language_id, @PathVariable Long user_id) {
//		return reviewservice.findReviewByUserIdAndLanguageId(user_id, language_id);
//	}
	
//	//리뷰 공감, 비공감
//	@GetMapping("/review/symp/findAll/{language_id}")
//	@ApiOperation(value = "리뷰 공감 리스트")
//	@ResponseBody
//	public List<SympDTO> sympList() {
//		return reviewservice.findAllSymp();
//	}
	
	@PostMapping("/review/symp/save/{review_id}")
	@ApiOperation(value = "하나의 리뷰 공감 누름")
	public boolean saveSymp(@PathVariable Long review_id, @RequestParam Long user_id) {
		return reviewservice.saveSymp(review_id, user_id);
	}
	
//	@GetMapping("/review/symp/findAll/{language_id}/user/{user_id}")
//	@ApiOperation(value = "사용자가 공감 누른 리뷰 리스트")
//	@ResponseBody
//	public List<SympDTO> findSympListByUserId(@PathVariable Long language_id, @PathVariable Long user_id) {
//		return reviewservice.findByLanguageIdAndUserId(language_id, user_id);
//	}
	
	@DeleteMapping("/review/symp/delete")
	@ApiOperation(value = "리뷰 공감 해제")
	public void deleteSymp(@RequestParam Long review_id, @RequestParam Long user_id) {
		reviewservice.deleteSymp(review_id, user_id);
	}
	
//	@GetMapping("/review/unsymp/findAll")
//	@ApiOperation(value = "리뷰 비공감 리스트")
//	@ResponseBody
//	public List<UnsympDTO> unsympList() {
//		return reviewservice.findAllUnsymp();
//	}
	
	@PostMapping("/review/unsymp/save/{review_id}")
	@ApiOperation(value = "하나의 리뷰 비공감 누름")
	public boolean saveUnsymp(@PathVariable Long review_id, @RequestParam Long user_id) {
		return reviewservice.saveUnsymp(review_id, user_id);
	}
	
//	@GetMapping("/review/unsymp/findAll/{language_id}/user/{user_id}")
//	@ApiOperation(value = "사용자가 비공감 누른 리뷰 리스트")
//	@ResponseBody
//	public List<UnsympDTO> findUnsympByUserId(@PathVariable Long user_id) {
//		return reviewservice.findUnsympByUserId(user_id);
//	}
	
	@DeleteMapping("/review/unsymp/delete")
	@ApiOperation(value = "리뷰 비공감 해제")
	public void deleteUnsymp(@RequestParam Long review_id, @RequestParam Long user_id) {
		reviewservice.deleteUnsymp(review_id, user_id);
	}
}
