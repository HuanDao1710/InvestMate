package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.IncomeStatementEntity;

@Repository
public interface IncomeStatementRepository extends JpaRepository<IncomeStatementEntity, Long> {
}