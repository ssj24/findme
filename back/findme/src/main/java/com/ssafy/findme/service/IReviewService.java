package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.ReviewDTO;
import com.ssafy.findme.dto.SympDTO;
import com.ssafy.findme.dto.UnsympDTO;

public interface IReviewService {
	void save(Long user_id, String content, Long language_id);
	void update(Long review_id, String content);
	void delete(Long review_id);
//	ReviewDTO findById(Long review_id);
	List<ReviewDTO> findAllByLanauageId(Long language_id, Long user_id);
//	List<ReviewDTO> findReviewByUserIdAndLanguageId(Long user_id, Long language_id);
	
//	List<SympDTO> findAllSymp();
	boolean saveSymp(Long review_id, Long user_id);
//	List<SympDTO> findByLanguageIdAndUserId(Long language_ig, Long user_id);
	void deleteSymp(Long review_id, Long user_id);
	
//	List<UnsympDTO> findAllUnsymp();
	boolean saveUnsymp(Long review_id, Long user_id);
//	List<UnsympDTO> findUnsympByUserId(Long user_id);
	void deleteUnsymp(Long review_id, Long user_id);
}
