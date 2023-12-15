package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {
	CompanyEntity findOneByCode(String code);
	@Modifying
	@Query("DELETE FROM CompanyEntity")
	void deleteAllData();
}

