package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.SaraminData;

@Repository
public interface SaraminRepository extends JpaRepository<SaraminData, Long> {

	List<SaraminData> findByJavasTrue();

	List<SaraminData> findByAssemblysTrue();

	List<SaraminData> findByCppsTrue();

	List<SaraminData> findByCsTrue();

	List<SaraminData> findByCsharpsTrue();

	List<SaraminData> findByDelphisTrue();

	List<SaraminData> findByGosTrue();

	List<SaraminData> findByJssTrue();

	List<SaraminData> findByMatlabsTrue();

	List<SaraminData> findByOcsTrue();

	List<SaraminData> findByPhpsTrue();

	List<SaraminData> findByPerlsTrue();

	List<SaraminData> findByPlsTrue();

	List<SaraminData> findByPythonsTrue();

	List<SaraminData> findByRsTrue();

	List<SaraminData> findByRubysTrue();

	List<SaraminData> findBySqlsTrue();

	List<SaraminData> findBySwiftsTrue();

	List<SaraminData> findByVbnsTrue();

	List<SaraminData> findByVbsTrue();

	void deleteByDueDateBefore(long now);

}
