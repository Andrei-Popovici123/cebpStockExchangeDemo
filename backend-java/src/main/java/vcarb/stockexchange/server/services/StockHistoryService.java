package vcarb.stockexchange.server.services;

import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Scheduled(fixedRate = 20000)
    @Transactional
    public void updateStockHistory() {
        List<StockEntity> stocks = stockRepository.findAll();

        for (StockEntity stock : stocks) {
            updatePeriodicStockHistory(stock);
        }
    }
    @Transactional
    public StockHistoryEntity updatePeriodicStockHistory(StockEntity stock){
        return stockHistoryRepository.save( new StockHistoryEntity(
                stock.getPrice(),
                stock,
                stock.getPrice(),
                stock.getPrice(),
                stock.getPrice(),
                stock.getAmount(),
                LocalDateTime.now(ZoneOffset.UTC)
        ));


    }
    public StockHistoryEntity updateStockHistory(Long id,StockHistoryDTO stockHistoryDTO){
        return stockHistoryRepository.findById(id)
                .map( stockHistory -> {
                    stockHistory.setPrice_closed(stockHistoryDTO.price_closed);
                    stockHistory.setPrice_high(stockHistoryDTO.price_high);
                    stockHistory.setPrice_low(stockHistoryDTO.price_low);
                    stockHistory.setPrice_open(stockHistoryDTO.price_open);
                    stockHistory.setUnits_traded(stockHistoryDTO.units_traded);
                    return stockHistoryRepository.save(stockHistory);
                })
                .orElseThrow(()-> new RuntimeException("Unable tho fetch Stock History"));
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
