package com.ssafy.findme.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Recruit;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Recruit r WHERE r.due_date < :due_date", nativeQuery = true)
	public void deleteByDueDate(@Param("due_date") Long due_date);

	public Recruit findById(long parseLong);

	@Query(value = "SELECT * FROM Recruit r WHERE r.due_date < :due_date", nativeQuery = true)
	public List<Recruit> FindIdByDueDate(@Param("due_date") Long due_date);

}
