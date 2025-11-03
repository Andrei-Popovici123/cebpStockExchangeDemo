package vcarb.stockexchange.server.dto;

public class TransactionDTO {
    public Integer type;     // 0 = BUY, 1 = SELL
    public Long userId;
    public double amount;
    public double totalPrice;

    public Long stockId;
}
