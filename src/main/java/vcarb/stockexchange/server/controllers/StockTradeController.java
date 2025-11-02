package vcarb.stockexchange.server.controllers;

import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.entities.Transaction;
import vcarb.stockexchange.server.services.StockService;

@RestController
@RequestMapping("/api/trade")
public class StockTradeController {
    private final StockService stockService;

    public StockTradeController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/buy/{stockId}")
    public Transaction buyStock(
            @PathVariable Long stockId,
            @RequestParam Long userId,
            @RequestParam double amount
            ){
        return stockService.buyStock(stockId, userId, amount);
    }

    @PostMapping("/sell/{stockId}")
    public Transaction sellStock(
            @PathVariable Long stockId,
            @RequestParam Long userId,
            @RequestParam double amount) {
        return stockService.sellStock(stockId, userId, amount);
    }

}
