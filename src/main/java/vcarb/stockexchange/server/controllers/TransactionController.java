package vcarb.stockexchange.server.controllers;

import io.swagger.v3.core.util.Json;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.entities.TransactionEntity;
import vcarb.stockexchange.server.repositories.TransactionRepository;


import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    public final TransactionRepository transactionRepository;
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/getAll")
    public List<TransactionEntity> getAllTransaction()
    {
        return transactionRepository.findAll();
    }

    @GetMapping("/getTransactionById/{transactionID}")
    public ResponseEntity<TransactionEntity> getTransaction(@PathVariable long transactionID){
        return transactionRepository.findById(transactionID).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createTransaction")
    public TransactionEntity createTransaction(@RequestBody TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("updateTransactionById/{transactionId}")
    public ResponseEntity<TransactionEntity> updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionEntity updatedTransaction) {
        return null;
    }

    @DeleteMapping("deleteTransactionById/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
