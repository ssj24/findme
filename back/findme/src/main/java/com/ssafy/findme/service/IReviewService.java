package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.ReviewDTO;
import com.ssafy.findme.dto.SympDTO;
import com.ssafy.findme.dto.UnsympDTO;

public interface IReviewService {
	void save(Long user_id, String content, Long language_id);
	void update(Long review_id, String content);
	void delete(Long review_id);
	ReviewDTO findById(Long review_id);
	List<ReviewDTO> findAllByLanauageId(Long language_id);
	List<ReviewDTO> findReviewByUserIdAndLanguageId(Long user_id, Long language_id);
	
	List<SympDTO> findAllSymp();
	void saveSymp(Long review_id, Long user_id);
	List<SympDTO> findSympByUserId(Long user_id);
	void deleteSymp(Long review_id, Long user_id);
	
	List<UnsympDTO> findAllUnsymp();
	void saveUnsymp(Long review_id, Long user_id);
	List<UnsympDTO> findUnsympByUserId(Long user_id);
	void deleteUnsymp(Long review_id, Long user_id);
}
