package vcarb.stockexchange.server.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.DTOs.StockCreateDTO;
import vcarb.stockexchange.server.entities.Stock;
import vcarb.stockexchange.server.entities.Transaction;
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

    @Transactional
    public synchronized Transaction buyStock(Long stockId, Long userId, double amount){
        Stock stock = stockRepository.findByIdForUpdate(stockId);
        if(stock.getAmount() < amount){
            throw new RuntimeException("Not enough stock");
        }

        stock.setAmount(stock.getAmount() - amount);
        stock.setPrice(stock.getPrice() + (1 + stock.getAprecCoef()));
        stockRepository.save(stock);

        double totalPrice = stock.getPrice() + amount;

        Transaction transaction = new Transaction(0, stock, userId, amount, totalPrice);
        System.out.println("Transaction: " +
                (transaction.getType() == 0 ? "BUY" : "SELL") +
                " | User: " + userId +
                " | Stock: " + stock.getName() +
                " | Amount: " + amount +
                " | TotalPrice: " + totalPrice);

        return transactionRepository.save(transaction);
    }

    @Transactional
    public synchronized Transaction sellStock(Long stockId, Long userId, double amount) {
        Stock stock = stockRepository.findByIdForUpdate(stockId);

        // Add amount and decrease price
        stock.setAmount(stock.getAmount() + amount);
        stock.setPrice(stock.getPrice() * (1 - stock.getAprecCoef()));
        stockRepository.save(stock);

        double totalPrice = stock.getPrice() * amount;

        Transaction transaction = new Transaction(1, stock, userId, amount, totalPrice);

        System.out.println("Transaction: " +
                (transaction.getType() == 0 ? "BUY" : "SELL") +
                " | User: " + userId +
                " | Stock: " + stock.getName() +
                " | Amount: " + amount +
                " | TotalPrice: " + totalPrice);

        return transactionRepository.save(transaction);
    }

}
