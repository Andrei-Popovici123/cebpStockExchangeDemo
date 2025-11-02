package vcarb.stockexchange.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vcarb.stockexchange.server.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

}
