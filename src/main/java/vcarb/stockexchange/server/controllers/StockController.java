package vcarb.stockexchange.server.controllers;

import io.swagger.v3.core.util.Json;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.MainController;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.repositories.StockRepository;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    public final StockRepository stockRepository;
    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/getAll")
    public List<StockEntity> getAllStock()
    {
        return stockRepository.findAll();
    }

    @GetMapping("/getStockById/{stockID}")
    public ResponseEntity<StockEntity> getStock(@PathVariable long stockID){
        return stockRepository.findById(stockID).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createStock")
    public StockEntity createStock(@RequestBody StockEntity stock) {
        return stockRepository.save(stock);
    }

    @PutMapping("updateStockById/{stockID}")
    public ResponseEntity<StockEntity> updateStock(@PathVariable Long stockID, @RequestBody StockEntity updatedStock) {
        return stockRepository.findById(stockID)
                .map(stock -> {
                    stock.setName(updatedStock.getName());
                    stock.setAmount(updatedStock.getAmount());
                    stock.setPrice(updatedStock.getPrice());
                    stock.setApreCoef(updatedStock.getApreCoef());
                    return ResponseEntity.ok(stockRepository.save(stock));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("deleteStockById/{stockID}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long stockID) {
        if (stockRepository.existsById(stockID)) {
            stockRepository.deleteById(stockID);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
