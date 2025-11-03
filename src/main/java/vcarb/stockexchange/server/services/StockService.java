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
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final TransactionRepository transactionRepository;

    private Map<Long, Double> userBalances = new ConcurrentHashMap<>();

    public StockService(StockRepository stockRepository, TransactionRepository transactionRepository) {

        this.stockRepository = stockRepository;
        this.transactionRepository = transactionRepository;

        userBalances.put(10L, 1000.0);
        userBalances.put(11L, 50.0);
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
    private final Random random = new Random();
    @Transactional
    public synchronized void simulateRandomPrice(Long stockId) {
        StockEntity stock = stockRepository.findByIdForUpdate(stockId);
        if (stock == null) return;
        // Random delta between -5 and 5
        double delta = -5 + 10 * random.nextDouble();

        // Apply appreciation coefficient
        delta *= (1 + stock.getApreCoef());

        // Update the stock price
        stock.setPrice(stock.getPrice() + delta);
        stockRepository.save(stock);

    }


    @Transactional
    public synchronized TransactionEntity buyStock(Long stockId, Long userId, int amount){
        StockEntity stock = stockRepository.findByIdForUpdate(stockId);
        if(stock.getAmount() < amount){
            throw new RuntimeException("Not enough stock");
        }
        double totalPrice = stock.getPrice() + amount;

        double balance = userBalances.getOrDefault(userId, 0.0);
        if(balance < totalPrice){
            throw new RuntimeException("Not enough balance for user " + userId);
        }

        stock.setAmount(stock.getAmount() - amount);
        stock.setPrice(stock.getPrice() + (1+ stock.getApreCoef()));
        stockRepository.save(stock);

        TransactionEntity transaction = new TransactionEntity(0, stock, userId, amount, totalPrice);
        transactionRepository.save(transaction);

        System.out.println("Transaction: " +
                (transaction.getType() == 0 ? "BUY" : "SELL") +
                " | User: " + userId +
                " | Stock: " + stock.getName() +
                " | Amount: " + amount +
                " | TotalPrice: " + totalPrice);

        return transaction;
    }

    @Transactional
    public synchronized TransactionEntity sellStock(Long stockId, Long userId, int amount){
        StockEntity stock = stockRepository.findByIdForUpdate(stockId);

        stock.setAmount(stock.getAmount() + amount);
        stock.setPrice(stock.getPrice() + (1- stock.getApreCoef()));
        stockRepository.save(stock);

        double totalPrice = stock.getPrice() + amount;

        TransactionEntity transaction = new TransactionEntity(0, stock, userId, amount, totalPrice);
        transactionRepository.save(transaction);

        System.out.println("Transaction: " +
                (transaction.getType() == 0 ? "BUY" : "SELL") +
                " | User: " + userId +
                " | Stock: " + stock.getName() +
                " | Amount: " + amount +
                " | TotalPrice: " + totalPrice);

        return transaction;
    }

    public Map<Long, Double> getUserBalances() {
        return userBalances;
    }
}
