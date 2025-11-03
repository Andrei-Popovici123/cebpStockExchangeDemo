package vcarb.stockexchange.server.services;

import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.dto.StockDTO;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.repositories.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;


    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockEntity> getAllStocks() {
        return stockRepository.findAll();
    }

    public Optional<StockEntity> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public StockEntity createStock(StockDTO stockDTO) {
        return stockRepository.save(new StockEntity(
                stockDTO.name,
                stockDTO.amount,
                stockDTO.price,
                stockDTO.apreCoef
        ));

    }

    public StockEntity updateStock(Long id, StockDTO stockDTO) {
        return stockRepository.findById(id)
                .map(stock -> {
                    stock.setName(stockDTO.name);
                    stock.setAmount(stockDTO.amount);
                    stock.setPrice(stockDTO.price);
                    stock.setApreCoef(stockDTO.apreCoef);
                    return stockRepository.save(stock);
                })
                .orElseThrow(() -> new RuntimeException("Stock doesn't exist"));
    }

    public void deleteSock(Long id) {
        stockRepository.deleteById(id);
    }
}
