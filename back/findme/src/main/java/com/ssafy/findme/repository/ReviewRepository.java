package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Reviews;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Integer>{

}
