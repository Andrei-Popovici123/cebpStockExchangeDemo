package vcarb.stockexchange.server.controllers;

import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.DTOs.StockCreateDTO;
import vcarb.stockexchange.server.entities.Stock;
import vcarb.stockexchange.server.services.StockService;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stock getStockById(@PathVariable Long id) {
        return stockService.getStockById(id)
                .orElseThrow(() -> new RuntimeException("Stock doesn't exists"));
    }

    @PostMapping
    public Stock createStock(@RequestBody StockCreateDTO stock) {
        return stockService.createStock(stock);
    }

    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        return stockService.updateStock(id, stock);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
    }
}
