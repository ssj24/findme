package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.SaraminData;

@Repository
public interface SaraminRepository extends JpaRepository<SaraminData, Long> {

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`java`", nativeQuery = true)
	List<SaraminData> findIdByJava();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`assembly`", nativeQuery = true)
	List<SaraminData> findIdByAssmbly();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`c++`", nativeQuery = true)
	List<SaraminData> findIdByCpp();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`c`", nativeQuery = true)
	List<SaraminData> findIdByC();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`c#`", nativeQuery = true)
	List<SaraminData> findIdByCsharp();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`delphi`", nativeQuery = true)
	List<SaraminData> findIdByDelphi();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`go`", nativeQuery = true)
	List<SaraminData> findIdByGo();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`java_script`", nativeQuery = true)
	List<SaraminData> findIdByJavaScript();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`matlab`", nativeQuery = true)
	List<SaraminData> findIdByMatlab();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`objective-c`", nativeQuery = true)
	List<SaraminData> findIdByObjective();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`perl`", nativeQuery = true)
	List<SaraminData> findIdByPearl();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`php`", nativeQuery = true)
	List<SaraminData> findIdByPhp();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`pl/sql`", nativeQuery = true)
	List<SaraminData> findIdByPl();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`python`", nativeQuery = true)
	List<SaraminData> findIdByPython();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`r`", nativeQuery = true)
	List<SaraminData> findIdByR();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`ruby`", nativeQuery = true)
	List<SaraminData> findIdByRuby();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`sql`", nativeQuery = true)
	List<SaraminData> findIdBySql();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`swift`", nativeQuery = true)
	List<SaraminData> findIdBySwift();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`vb_net`", nativeQuery = true)
	List<SaraminData> findIdByVb();

	@Query(value = "SELECT * FROM Saramin_data s WHERE s.`visual basic`", nativeQuery = true)
	List<SaraminData> findIdByVisual();

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

	List<SaraminData> findByVbs();

}
