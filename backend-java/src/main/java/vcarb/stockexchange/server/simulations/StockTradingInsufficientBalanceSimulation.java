package vcarb.stockexchange.server.simulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vcarb.stockexchange.server.services.StockService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StockTradingInsufficientBalanceSimulation {

    @Autowired
    private StockService stockService;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    @Scheduled(fixedRate = 15000)
    public void simulateInsufficientBalance() {
        Long stockId = 1L;
        Long user1 = 10L;
        Long user2 = 11L;

        executor.submit(() -> {
            try {
                stockService.buyStock(stockId, user1, 3);
                System.out.println("User 1 successfully bought stock");
            } catch (Exception e) {
                System.out.println("User 1 failed: " + e.getMessage());
            }
        });

        //This should fail
        executor.submit(() -> {
            try {
                stockService.buyStock(stockId, user2, 5);
                System.out.println("User 2 successfully bought stock");
            } catch (Exception e) {
                System.out.println("User 2 failed: " + e.getMessage());
            }
        });

        stockService.getUserBalances().forEach((id, balance) -> {System.out.println("Userid with id " + id +" has balance " + balance);});
    }
}
