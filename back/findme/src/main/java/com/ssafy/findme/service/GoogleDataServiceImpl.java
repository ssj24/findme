package com.ssafy.findme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.dto.GoogleTrendDataDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.GoogleTrendRepository;

@Service
public class GoogleDataServiceImpl implements IGoogleDataService {
	@Autowired
	private GoogleTrendRepository googletrendrepo;

	@Autowired
	private EntityMapper entityMapper;
	
	@Override
	public List<GoogleTrendDataDTO> findAllByDate() {
		return googletrendrepo.findAll().stream().map(e->entityMapper.convertToDomain(e, GoogleTrendDataDTO.class))
		.collect(Collectors.toList());
	}

}
