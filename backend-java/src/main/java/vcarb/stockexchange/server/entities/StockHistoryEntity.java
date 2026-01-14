package vcarb.stockexchange.server.entities;

import jakarta.persistence.*;
import org.apache.logging.log4j.util.Lazy;

import java.time.LocalDateTime;

@Entity
@Table(name = "stockHistory")
public class StockHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    double price_open;
    @Column(nullable = false)
    double price_high;
    @Column(nullable = false)
    double price_low;
    @Column(nullable = false)
    double price_closed;
    @Column(nullable = false)
    int units_traded;

    @Column(nullable = false)
    LocalDateTime timeframe;

    @OneToOne(mappedBy = "stockHistory")
    private StockEntity stock;

    public StockHistoryEntity(double price_open, double price_closed, double price_high, double price_low, int units_traded, LocalDateTime timeframe) {
        this.timeframe = timeframe;
        this.units_traded = units_traded;
        this.price_closed = price_closed;
        this.price_high = price_high;
        this.price_low = price_low;
        this.price_open = price_open;
    }

    public Long getId(){return id;}

    public double getPrice_open() {
        return price_open;
    }

    public void setPrice_open(double price_open) {
        this.price_open = price_open;
    }

    public double getPrice_high() {
        return price_high;
    }

    public void setPrice_high(double price_high) {
        this.price_high = price_high;
    }

    public double getPrice_low() {
        return price_low;
    }

    public void setPrice_low(double price_low) {
        this.price_low = price_low;
    }

    public double getPrice_closed() {
        return price_closed;
    }

    public void setPrice_closed(double price_closed) {
        this.price_closed = price_closed;
    }

    public int getUnits_traded() {
        return units_traded;
    }

    public void setUnits_traded(int units_traded) {
        this.units_traded = units_traded;
    }

    public LocalDateTime getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(LocalDateTime timeframe) {
        this.timeframe = timeframe;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
    }
}
