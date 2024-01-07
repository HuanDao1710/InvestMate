package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.StockFilterEntity;

@Repository
public interface StockFilterRepository extends JpaRepository<StockFilterEntity, Long> {
	StockFilterEntity findOneByCompanyEntity(CompanyEntity companyEntity);
}
