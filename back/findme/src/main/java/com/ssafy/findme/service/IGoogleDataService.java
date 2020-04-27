package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.GoogleTrendDataDTO;

public interface IGoogleDataService {
	List<GoogleTrendDataDTO> findAllByDate();
}
