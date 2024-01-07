package vn.edu.hust.investmate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CashFlowEntity;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;

import java.util.List;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlowEntity, Long> {
	@Query("SELECT e FROM CashFlowEntity e " +
			"WHERE e.companyEntity = :companyEntity AND e.yearly = :yearly")
	List<CashFlowEntity> findAllByYearAndQuarter(@Param("companyEntity") CompanyEntity companyEntity, @Param("yearly") int yearly, Sort sort);

}
