package vcarb.stockexchange.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vcarb.stockexchange.server.dto.StockHistoryDTO;
import vcarb.stockexchange.server.entities.StockHistoryEntity;
import vcarb.stockexchange.server.services.StockHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/stock_history")
public class StockHistoryController {
    public final StockHistoryService stockHistoryService;

    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping
    public List<StockHistoryEntity> getAllStockHistory(){ return stockHistoryService.getAllStockHistory();}

    @GetMapping("/{stockHistoryId}")
    public StockHistoryEntity getStockHistoryById(@PathVariable long stockHistoryId){
        return stockHistoryService.getStockHistoryById(stockHistoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock History not found"));
    }
    @GetMapping("/stocks/{stockId}")
    public StockHistoryEntity getStockHistoryByStockId(@PathVariable long stockId){
        return stockHistoryService.getStockHistoryByStockId(stockId);
    }
    @PutMapping("/{stockHistoryId}")
    public StockHistoryEntity updateStockHistory(@PathVariable long stockHistoryId, @RequestBody StockHistoryDTO stockHistoryDTO){
        return stockHistoryService.updateStockHistory(stockHistoryId,stockHistoryDTO);
    }
    @PostMapping
    public StockHistoryEntity createStockHistory(@RequestBody StockHistoryDTO stockHistoryDTO){
        return stockHistoryService.createStockHistory(stockHistoryDTO);
    }
    @DeleteMapping("/{stockHistoryId}")
    public void deleteStockHistory( @PathVariable long stockHistoryId){
        stockHistoryService.deleteStockHistory(stockHistoryId);
    }


}
