package vcarb.stockexchange.server.services;

import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.DTOs.StockCreateDTO;
import vcarb.stockexchange.server.entities.Stock;
import vcarb.stockexchange.server.repositories.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id){
        return stockRepository.findById(id);
    }

    public Stock createStock(StockCreateDTO stock){
        return stockRepository.save(new Stock(
                stock.name,
                stock.amount,
                stock.price,
                stock.aprecCoef
        ));
    }

    public Stock updateStock(Long id, Stock newStock){
        return stockRepository.findById(id)
                .map(stock ->  {
                    stock.setName(newStock.getName());
                    stock.setAmount(newStock.getAmount());
                    stock.setPrice(newStock.getPrice());
                    stock.setAprecCoef(newStock.getAprecCoef());
                    return stockRepository.save(stock);
                })
                .orElseThrow(() -> new RuntimeException("Stock doesn't exist"));
    }

    public void deleteStock(Long id){
        stockRepository.deleteById(id);
    }
}
