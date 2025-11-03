package vcarb.stockexchange.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vcarb.stockexchange.server.entities.TransactionEntity;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
     List<TransactionEntity> findByStockId(Long stockId);
}
