package vn.edu.hust.investmate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CashFlowEntity;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.FinancialRatioEntity;
import vn.edu.hust.investmate.domain.entity.IncomeStatementEntity;

import java.util.List;

@Repository
public interface IncomeStatementRepository extends JpaRepository<IncomeStatementEntity, Long> {
	@Query("SELECT e FROM IncomeStatementEntity e " +
			"WHERE e.companyEntity = :companyEntity AND e.yearly = :yearly")
	List<IncomeStatementEntity> findAllByYearAndQuarter(@Param("companyEntity") CompanyEntity companyEntity, @Param("yearly") int yearly, Sort sort);

}
