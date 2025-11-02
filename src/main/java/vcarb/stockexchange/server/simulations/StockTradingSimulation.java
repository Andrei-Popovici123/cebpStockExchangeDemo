package vcarb.stockexchange.server.simulations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vcarb.stockexchange.server.services.StockService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StockTradingSimulation implements CommandLineRunner {

    private final StockService stockService;
    public StockTradingSimulation(StockService stockService) {
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
                stockService.sellStock(stockId, user1, 5);
                System.out.println("User 1 bought stock");
            }catch (Exception e){
                System.out.println("User 1 failed" + e.getMessage());
            }
        });

        executor.submit(() -> {
            try{
                stockService.sellStock(stockId, user2, 10);
                System.out.println("User 2 bought stock");
            } catch (Exception e){
                System.out.println("User 2 failed" + e.getMessage());
            }
        });

        executor.shutdown();
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleSimulation() {
        System.out.println("\nStarting new concurrent transaction simulation...");
        run();
    }
}
