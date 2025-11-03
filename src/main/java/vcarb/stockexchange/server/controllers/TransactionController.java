package vcarb.stockexchange.server.controllers;

import io.swagger.v3.core.util.Json;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.dto.TransactionDTO;
import vcarb.stockexchange.server.entities.TransactionEntity;
import vcarb.stockexchange.server.repositories.TransactionRepository;
import vcarb.stockexchange.server.services.TransactionService;


import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    public final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionEntity> getAllTransaction()
    {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionID}")
    public TransactionEntity getTransaction(@PathVariable long transactionID){
        return transactionService.getTransactionById(transactionID)
                .orElseThrow(()-> new RuntimeException("Transaction doesn't exist"));
    }
    @GetMapping("/stocks/{stockID}")
    public List<TransactionEntity> getTransactionByStockId(@PathVariable long stockID){
        return transactionService.getTransactionsByStockId(stockID);
    }

    @PostMapping
    public TransactionEntity createTransaction(@RequestBody TransactionDTO transaction) {
        return transactionService.createTransaction(transaction);
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}
