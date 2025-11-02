package vcarb.stockexchange.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vcarb.stockexchange.server.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByStockId(Long stockId);
}
