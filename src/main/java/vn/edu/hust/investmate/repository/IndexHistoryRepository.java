package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.IndexHistoryEntity;

@Repository
public interface IndexHistoryRepository extends JpaRepository<IndexHistoryEntity, Long> {
	@Query("SELECT MAX(ih.time) FROM IndexHistoryEntity ih WHERE ih.indexEntity.comGroupCode = :comGroupCode")
	Long findMaxTimeByCode(@Param("comGroupCode") String code);
}
