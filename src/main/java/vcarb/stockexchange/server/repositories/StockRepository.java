package vcarb.stockexchange.server.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import vcarb.stockexchange.server.entities.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM StockEntity s WHERE s.stockId = :id")
    StockEntity findByIdForUpdate(Long id);

}
