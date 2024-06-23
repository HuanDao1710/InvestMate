package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.CompanyOverviewEntity;

import java.util.List;

@Repository
public interface CompanyOverviewRepository extends JpaRepository<CompanyOverviewEntity, String> {
	List<CompanyOverviewEntity> findByCompanyEntity(CompanyEntity companyEntity);

}
