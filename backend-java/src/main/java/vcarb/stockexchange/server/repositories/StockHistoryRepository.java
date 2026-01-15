package vcarb.stockexchange.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vcarb.stockexchange.server.entities.StockHistoryEntity;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistoryEntity,Long> {
    StockHistoryEntity findHistoryByStockId(Long stockId);
}
