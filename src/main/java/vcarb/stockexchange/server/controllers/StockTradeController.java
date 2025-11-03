package vcarb.stockexchange.server.controllers;

import org.springframework.web.bind.annotation.*;
import vcarb.stockexchange.server.entities.TransactionEntity;
import vcarb.stockexchange.server.services.StockService;

@RestController
@RequestMapping("api/trade")
public class StockTradeController {
    private final StockService stockService;

    public StockTradeController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/buy/{stockId}")
    public TransactionEntity buyStock(
            @PathVariable Long stockId,
            @RequestParam Long userId,
            @RequestParam int amount
            ){
        return stockService.buyStock(stockId, userId, amount);
    }

    @PostMapping("/sell/{stockId}")
    public TransactionEntity sellStock(
            @PathVariable Long stockId,
            @RequestParam Long userId,
            @RequestParam int amount
    ){
        return stockService.sellStock(stockId, userId, amount);
    }
}
