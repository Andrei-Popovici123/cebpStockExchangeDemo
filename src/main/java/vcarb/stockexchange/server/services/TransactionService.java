package vcarb.stockexchange.server.services;

import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.dto.StockDTO;
import vcarb.stockexchange.server.dto.TransactionDTO;
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

    public TransactionEntity createTransaction(TransactionDTO transactionDTO) {

        return transactionRepository.save( new TransactionEntity(
                transactionDTO.type,
                stockRepository.findById(transactionDTO.stockId)
                        .orElseThrow(() -> new RuntimeException("Stock not found with ID")),
                transactionDTO.userId,
                transactionDTO.amount,
                transactionDTO.totalPrice
        ));
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }


}
