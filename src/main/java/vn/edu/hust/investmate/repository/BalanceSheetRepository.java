package vn.edu.hust.investmate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.BalanceSheetEntity;
import vn.edu.hust.investmate.domain.entity.CashFlowEntity;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;

import java.util.List;

@Repository
public interface BalanceSheetRepository extends JpaRepository<BalanceSheetEntity, Long> {
	@Query("SELECT e FROM BalanceSheetEntity e " +
			"WHERE e.companyEntity = :companyEntity AND e.yearly = :yearly")
	List<BalanceSheetEntity> findAllByYearAndQuarter(@Param("companyEntity") CompanyEntity companyEntity, @Param("yearly") int yearly, Sort sort);

}
