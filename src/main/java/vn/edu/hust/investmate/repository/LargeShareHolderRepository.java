package vn.edu.hust.investmate.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.LargeShareHolderEntity;

@Repository
public interface LargeShareHolderRepository extends	JpaRepository<LargeShareHolderEntity, String> {
	@Modifying
	@Transactional
	void deleteByCompanyEntity(CompanyEntity companyEntity);
}
