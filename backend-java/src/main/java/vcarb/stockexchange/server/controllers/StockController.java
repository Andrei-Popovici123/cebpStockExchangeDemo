package vcarb.stockexchange.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.dto.StockDTO;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.repositories.StockRepository;
import vcarb.stockexchange.server.services.StockService;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    public final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping()
    public List<StockEntity> getAllStock()
    {
        return stockService.getAllStocks();
    }

    @GetMapping("/{stockID}")
    public StockEntity getStock(@PathVariable long stockID){
        return stockService.getStockById(stockID)
                .orElseThrow(()-> new RuntimeException("Stock doesn't exist"));
    }

    @PostMapping
    public StockEntity createStock(@RequestBody StockDTO stock) {
        return stockService.createStock(stock);
    }

    @PutMapping("/{stockID}")
    public StockEntity updateStock(@PathVariable Long stockID, @RequestBody StockDTO updatedStock) {
        return stockService.updateStock(stockID, updatedStock);
    }

    @DeleteMapping("/{stockID}")
    public void deleteStock(@PathVariable Long stockID) {
        stockService.deleteSock(stockID);
    }
}
