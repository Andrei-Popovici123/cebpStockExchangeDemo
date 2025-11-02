package vcarb.stockexchange.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vcarb.stockexchange.server.entities.Stock;

public interface StockRepository extends JpaRepository<Stock,Long> {
}
