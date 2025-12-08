package vcarb.stockexchange.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int amount;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TransactionEntity> transactions;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double apreCoef = 0.1;

    public StockEntity(){}

    public StockEntity(String name, int amount, double price, double apreCoef) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.apreCoef = apreCoef;
    }

    public Long getId() { return stockId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getApreCoef() { return apreCoef; }
    public void setApreCoef(double apreCoef) { this.apreCoef = apreCoef; }

    public List<TransactionEntity> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionEntity> transactions) { this.transactions = transactions; }
}
