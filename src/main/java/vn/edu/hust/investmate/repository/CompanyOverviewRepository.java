package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hust.investmate.domain.entity.CompanyOverviewEntity;

public interface CompanyOverviewRepository extends JpaRepository<CompanyOverviewEntity, String> {
	@Modifying
	@Query("DELETE FROM OverviewCompanyEntity")
	void deleteAllData();
}
