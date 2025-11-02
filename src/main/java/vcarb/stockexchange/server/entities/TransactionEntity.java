package vcarb.stockexchange.server.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction")

public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private int type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockId", nullable = false)
    private StockEntity stock;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    public TransactionEntity(){}

    public TransactionEntity(int type, StockEntity stock, Long userId, int amount, BigDecimal totalPrice) {
        this.type = type;
        this.stock = stock;
        this.userId = userId;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public Long getId() { return transactionId; }
    public int getType() { return type; }
    public void setType(int type) { this.type = type; }

    public StockEntity getStock() { return stock; }
    public void setStock(StockEntity stock) { this.stock = stock; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

}
