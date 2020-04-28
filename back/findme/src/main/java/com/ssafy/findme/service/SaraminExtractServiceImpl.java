package com.ssafy.findme.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.dto.SaraminExtractDTO;
import com.ssafy.findme.repository.RecruitRepository;

@Service
public class SaraminExtractServiceImpl implements ISaraminExtractService {

	@Autowired
	private RecruitRepository recruitrepo;

	public List<SaraminExtractDTO> adopted() {
		List<SaraminExtractDTO> list = new ArrayList<SaraminExtractDTO>();
		// select id from saramin_data where java=true;

		return list;
	}

}
