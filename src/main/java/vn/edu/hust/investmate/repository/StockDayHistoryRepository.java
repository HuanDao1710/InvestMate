package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.StockDayHistoryEntity;

import java.util.List;

@Repository
public interface StockDayHistoryRepository extends JpaRepository<StockDayHistoryEntity,Long > {
	@Query("SELECT MAX(sh.time) FROM StockDayHistoryEntity sh WHERE sh.companyEntity.code = :code")
	Long findMaxTimeByCode(@Param("code") String code);

	@Query("SELECT MAX(e.time) FROM StockDayHistoryEntity e WHERE e.time < :inputTime and e.companyEntity = :companyEntity")
	Long findMaxTimeBeforeAndCompanyEntity(@Param("inputTime") Long inputTime, @Param("companyEntity") CompanyEntity companyEntity);


	List<StockDayHistoryEntity> findByCompanyEntityAndTimeBetweenOrderByTimeAsc(CompanyEntity companyEntity, long from, long to);
	List<StockDayHistoryEntity> findByCompanyEntityAndTimeBetween(CompanyEntity companyEntity, long from, long to);
}
