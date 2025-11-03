package vcarb.stockexchange.server.simulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vcarb.stockexchange.server.services.StockService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StockPriceRandomizer {
    @Autowired
    private StockService stockService;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    @Scheduled(fixedRate = 23000)
    public void randomizePrices() {
        stockService.getAllStocks().forEach(stock -> {
            executor.submit(() -> stockService.simulateRandomPrice(stock.getId()));
        });
    }
}
