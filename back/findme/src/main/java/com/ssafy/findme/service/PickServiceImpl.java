package com.ssafy.findme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Pick;
import com.ssafy.findme.domain.Recruit;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.PickDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.repository.PickRepository;
import com.ssafy.findme.repository.RecruitRepository;

@Service
public class PickServiceImpl implements IPickService {
	
	@Autowired
	private PickRepository pickrepo;
	@Autowired
	private AccountRepository accountrepo;
	@Autowired
	private RecruitRepository recruitrepo;
	@Autowired
	private EntityMapper entityMapper;

	@Override
	public void savePick(Long user_id, Long recruit_id) {
		Pick pick = new Pick();
		User user = accountrepo.findById(user_id).orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
		pick.setUser(user);
		Recruit recruit = recruitrepo.findById(recruit_id).orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
		pick.setRecruit(recruit);
		pickrepo.save(pick);
	}

	@Override
	public List<PickDTO> findAllByUserId(Long user_id) {
		return pickrepo.findByUserId(user_id).stream().map(e->entityMapper.convertToDomain(e, PickDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deletePick(Long id) {
		try {
			pickrepo.delete(pickrepo.findById(id).get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
