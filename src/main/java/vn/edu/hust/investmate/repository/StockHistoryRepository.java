package vn.edu.hust.investmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.investmate.domain.entity.StockHistoryEntity;

public interface StockHistoryRepository extends JpaRepository<StockHistoryEntity, Long> {
}
