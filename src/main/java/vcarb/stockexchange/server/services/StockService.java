package vcarb.stockexchange.server.services;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.dto.StockDTO;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.entities.TransactionEntity;
import vcarb.stockexchange.server.repositories.StockRepository;
import vcarb.stockexchange.server.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final TransactionRepository transactionRepository;

    public StockService(StockRepository stockRepository, TransactionRepository transactionRepository) {

        this.stockRepository = stockRepository;
        this.transactionRepository = transactionRepository;
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

    @Transactional
    public synchronized TransactionEntity buyStock(Long stockId, Long userId, int amount){
        StockEntity stock = stockRepository.findByIdForUpdate(stockId);
        if(stock.getAmount() < amount){
            throw new RuntimeException("Not enough stock");
        }

        stock.setAmount(stock.getAmount() - amount);
        stock.setPrice(stock.getPrice() + (1+ stock.getApreCoef()));
        stockRepository.save(stock);

        double totalPrice = stock.getPrice() + amount;

        TransactionEntity transaction = new TransactionEntity(0, stock, userId, amount, totalPrice);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public synchronized TransactionEntity sellStock(Long stockId, Long userId, int amount){
        StockEntity stock = stockRepository.findByIdForUpdate(stockId);

        stock.setAmount(stock.getAmount() + amount);
        stock.setPrice(stock.getPrice() + (1- stock.getApreCoef()));
        stockRepository.save(stock);

        double totalPrice = stock.getPrice() + amount;

        TransactionEntity transaction = new TransactionEntity(0, stock, userId, amount, totalPrice);
        return transactionRepository.save(transaction);
    }
}
