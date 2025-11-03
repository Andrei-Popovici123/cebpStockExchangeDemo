package vcarb.stockexchange.server.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction")

public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    // e.g., 0 = BUY, 1 = SELL
    @Column(nullable = false)
    private Integer type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockId", nullable = false)
    private StockEntity stock;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double totalPrice;

    public TransactionEntity(){}

    public TransactionEntity(int type, StockEntity stock, Long userId, double amount, double totalPrice) {
        this.type = type;
        this.stock = stock;
        this.userId = userId;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public Long getId() { return transactionId; }
    public void SetID(Long id){ this.transactionId =id;}
    public int getType() { return type; }
    public void setType(int type) { this.type = type; }

    public StockEntity getStock() { return stock; }
    public void setStock(StockEntity stock) { this.stock = stock; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

}
