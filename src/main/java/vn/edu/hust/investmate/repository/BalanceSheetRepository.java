package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.investmate.domain.entity.BalanceSheetEntity;

@Repository
public interface BalanceSheetRepository extends JpaRepository<BalanceSheetEntity, Long> {
}