package vcarb.stockexchange.server.services;

import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.dto.StockDTO;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.entities.TransactionEntity;
import vcarb.stockexchange.server.repositories.StockRepository;
import vcarb.stockexchange.server.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final StockRepository stockRepository;


    public TransactionService(TransactionRepository transactionRepository, StockRepository stockRepository) {
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
    }
        public List<TransactionEntity> getAllTransactions() {
            return transactionRepository.findAll();
        }

        public Optional<TransactionEntity> getTransactionById(Long id) {
            return transactionRepository.findById(id);
        }

    public List<TransactionEntity> getTransactionsByStockId(Long stockId) {
        return transactionRepository.findByStockId(stockId);
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {
        StockEntity stock = stockRepository.findById(transaction.getStock().getId())
                .orElseThrow(() -> new RuntimeException("Stock doesn't exist"));

        transaction.setStock(stock);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }


}
