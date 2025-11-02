package vcarb.stockexchange.server.controllers;

import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.entities.Transaction;
import vcarb.stockexchange.server.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/stock/{stockId}")
    public List<Transaction> getTransactionsByStockId(@PathVariable Long stockId) {
        return transactionService.getTransactionsByStockId(stockId);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
