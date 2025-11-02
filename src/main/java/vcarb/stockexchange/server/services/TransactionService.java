package vcarb.stockexchange.server.services;

import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.entities.Stock;
import vcarb.stockexchange.server.entities.Transaction;
import vcarb.stockexchange.server.repositories.StockRepository;
import vcarb.stockexchange.server.repositories.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final StockRepository stockRepository;

    public TransactionService(TransactionRepository transactionRepository, StockRepository stockRepository) {
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByStockId(Long stockId) {
        return transactionRepository.findByStockId(stockId);
    }

    public Transaction createTransaction(Transaction transaction) {
        Stock stock = stockRepository.findById(transaction.getStock().getId())
                .orElseThrow(() -> new RuntimeException("Stock doesn't exist"));

        transaction.setStock(stock);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
