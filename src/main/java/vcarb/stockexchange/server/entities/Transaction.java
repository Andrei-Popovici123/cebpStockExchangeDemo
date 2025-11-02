package vcarb.stockexchange.server.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g., 0 = BUY, 1 = SELL
    @Column(nullable = false)
    private Integer type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Future foreign key to Users table

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double totalPrice;

    // Constructors
    public Transaction() {}

    public Transaction(Integer type, Stock stock, Long userId, Double amount, Double totalPrice) {
        this.type = type;
        this.stock = stock;
        this.userId = userId;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }

    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
