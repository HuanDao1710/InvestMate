package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.CompanyOverviewEntity;

@Repository
public interface CompanyOverviewRepository extends JpaRepository<CompanyOverviewEntity, String> {

}
