package vcarb.stockexchange.server.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vcarb.stockexchange.server.entities.StockEntity;
import vcarb.stockexchange.server.repositories.StockRepository;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class StockMonitorService {
    private final StockRepository stockRepository;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public StockMonitorService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void monitorStock() {
        List<StockEntity> stocks = stockRepository.findAll();
        System.out.println("\n=== STOCK STATUS UPDATE ===");
        for (StockEntity s : stocks) {
            System.out.println(s.getName() +
                    " | Amount: " + df.format(s.getAmount()) +
                    " | Price: " + df.format(s.getPrice()));
        }
        System.out.println("==============================\n");
    }
}
