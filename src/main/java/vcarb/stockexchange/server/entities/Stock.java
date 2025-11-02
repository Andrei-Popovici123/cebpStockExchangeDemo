package vcarb.stockexchange.server.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double aprecCoef = 0.1;

    // One stock can have many transactions
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    // Constructors
    public Stock() {}

    public Stock(String name, Double amount, Double price, Double aprecCoef) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.aprecCoef = aprecCoef;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getAprecCoef() { return aprecCoef; }
    public void setAprecCoef(double aprecCoef) { this.aprecCoef = aprecCoef; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}

