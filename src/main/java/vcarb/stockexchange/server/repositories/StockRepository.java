package vcarb.stockexchange.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vcarb.stockexchange.server.entities.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity,Long> {

}
