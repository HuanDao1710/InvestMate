package vn.edu.hust.investmate.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.TemporaryEntity;

import java.util.List;

@Repository
public interface TemporaryRepository extends JpaRepository<TemporaryEntity, Long> {
	@Transactional
	void deleteByCompanyEntity(CompanyEntity companyEntity);
//	List<Double> findAllRsRawByOrderByRsRaw_Asc();
	TemporaryEntity findOneByCompanyEntity(CompanyEntity companyEntity);
	@Query("SELECT t.rsRaw FROM TemporaryEntity t ORDER BY t.rsRaw ASC")
	List<Double> findRsRawOrderedAsc();

}
