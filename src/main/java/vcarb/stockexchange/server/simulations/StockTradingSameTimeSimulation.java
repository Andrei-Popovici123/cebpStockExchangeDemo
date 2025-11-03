package vcarb.stockexchange.server.simulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vcarb.stockexchange.server.services.StockService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StockTradingSameTimeSimulation implements CommandLineRunner {
    private final StockService stockService;

    public StockTradingSameTimeSimulation(@Autowired StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void run(String... args){
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Long stockId = 1L;
        Long user1 = 10L;
        Long user2 = 11L;

        executor.submit(() -> {
            try {
                stockService.buyStock(stockId, user1, 5);
                System.out.println("User1 bought stock");
            } catch (Exception e) {
                System.out.println("User1 failed: " + e.getMessage());
            }
        });

        executor.submit(() -> {
            try {
                stockService.buyStock(stockId, user2, 5);
                System.out.println("User2 bought stock");
            } catch (Exception e) {
                System.out.println("User2 failed: " + e.getMessage());
            }
        });

        executor.shutdown();
    }
}
