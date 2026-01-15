package vcarb.stockexchange.server.services;

import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.dto.StockHistoryDTO;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.entities.StockHistoryEntity;
import vcarb.stockexchange.server.repositories.StockHistoryRepository;
import vcarb.stockexchange.server.repositories.StockRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
@Service
public class StockHistoryService {
    private final StockHistoryRepository stockHistoryRepository;
    private final StockRepository stockRepository;


    public StockHistoryService(StockHistoryRepository stockHistoryRepository, StockRepository stockRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
        this.stockRepository = stockRepository;
    }
    public List<StockHistoryEntity> getAllStockHistory() {
        return stockHistoryRepository.findAll();
    }

    public Optional<StockHistoryEntity> getStockHistoryById(Long id) {
        return stockHistoryRepository.findById(id);
    }

    public StockHistoryEntity getStockHistoryByStockId(Long stockId) {
        return stockHistoryRepository.findHistoryByStockId(stockId);
    }

    public StockHistoryEntity createStockHistory(StockHistoryDTO stockHistoryDTO) {

        return stockHistoryRepository.save( new StockHistoryEntity(
                stockHistoryDTO.price_open,
                stockRepository.findById(stockHistoryDTO.stockId)
                        .orElseThrow(() -> new RuntimeException("Stock not found with ID")),
                stockHistoryDTO.price_closed,
                stockHistoryDTO.price_high,
                stockHistoryDTO.price_low,
                0,
                LocalDateTime.now(ZoneOffset.UTC)
        ));
    }

    public void deleteStockHistory(Long Id) {stockHistoryRepository.deleteById(Id);
    }

    public StockHistoryEntity createInitialHistory(StockEntity stock) {
        return stockHistoryRepository.save(new StockHistoryEntity(
                stock.getPrice(),
                stock,
                stock.getPrice(),
                stock.getPrice(),
                stock.getPrice(),
                0,
                LocalDateTime.now(ZoneOffset.UTC)));
    }
}
