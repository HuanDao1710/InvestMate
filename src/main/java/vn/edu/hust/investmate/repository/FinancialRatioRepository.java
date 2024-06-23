package vn.edu.hust.investmate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.FinancialRatioEntity;

import java.util.List;

@Repository
public interface FinancialRatioRepository extends	JpaRepository<FinancialRatioEntity, Long> {
	@Query("SELECT e FROM FinancialRatioEntity e " +
			"WHERE e.companyEntity = :companyEntity AND e.yearly = :yearly")
	List<FinancialRatioEntity> findAllByYearAndQuarter(@Param("companyEntity") CompanyEntity companyEntity, @Param("yearly") int yearly, Sort sort);

	List<FinancialRatioEntity> findByCompanyEntityAndYearlyAndQuarterAndYear(CompanyEntity companyEntity,int yearly, int quarter, int year);


}
