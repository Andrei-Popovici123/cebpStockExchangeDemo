package vcarb.stockexchange.server;

import io.swagger.v3.core.util.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
public class MainController {
    enum TransactionType
    {
        POSITIVE,
        NEGATIVE
    }

    static class Stock
    {
        private int stockId;
        private String stockName;
        private long amount;
        private long price;
        private double appreciationCoefficient;
        private double stockCoefficient;
        public Stock(int stockId, String stockName, long amount, long price, double appreciationCoefficient, double stockCoefficient) {
            this.stockId = stockId;
            this.stockName = stockName;
            this.amount = amount;
            this.price = price;
            this.appreciationCoefficient = appreciationCoefficient;
            this.stockCoefficient = stockCoefficient;
        }
        public int getStockId() {
            return stockId;
        }
        public void setStockId(int stockId) {
            this.stockId = stockId;
        }
        public String getStockName() {
            return stockName;
        }
        public void setStockName(String stockName) {
            this.stockName = stockName;
        }
        public long getAmount() {
            return amount;
        }
        public void setAmount(long amount) {
            this.amount = amount;
        }
        public long getPrice() {
            return price;
        }
        public void setPrice(long price) {
            this.price = price;
        }
        public double getAppreciationCoefficient() {
            return appreciationCoefficient;
        }
        public void setAppreciationCoefficient(double appreciationCoefficient) {
            this.appreciationCoefficient = appreciationCoefficient;
        }
        public double getStockCoefficient() {
            return stockCoefficient;
        }
        public void setStockCoefficient(double stockCoefficient) {
            this.stockCoefficient = stockCoefficient;
        }
    }

    static class Transaction
    {
        private long transactionId;
        private TransactionType transactionType;
        private int stockId;
        private int userID;
        private long amount;
        private long totalPrice;

        public Transaction (long transactionId, TransactionType transactionType, int stockId, int userID, long amount, long totalPrice)
        {
            this.transactionId = transactionId;
            this.transactionType = transactionType;
            this.stockId = stockId;
            this.userID = userID;
            this.amount = amount;
            this.totalPrice = totalPrice;
        }
        public long getTransactionId()
        {
            return transactionId;
        }
        public TransactionType getTransactionType()
        {
            return transactionType;
        }
        public int getStockId()
        {
            return stockId;
        }
        public int getUserID()
        {
            return userID;
        }
        public long getAmount()
        {
            return amount;
        }
        public long getTotalPrice()
        {
            return totalPrice;
        }
        public void setTransactionId(long transactionId)
        {
            this.transactionId = transactionId;
        }
        public void setTransactionType(TransactionType transactionType)
        {
            this.transactionType = transactionType;
        }
        public void setStockId(int stockId)
        {
            this.stockId = stockId;
        }
        public void setUserID(int userID)
        {
            this.userID = userID;
        }
        public void setAmount(long amount)
        {
            this.amount = amount;
        }
        public void setTotalPrice(long totalPrice)
        {
            this.totalPrice = totalPrice;
        }
    }

    public List<Transaction> transactionsList = new ArrayList<>();
    public List<Stock> stocksList = new ArrayList<>();

    private void setTransactionsList()
    {
        transactionsList.add(new Transaction(0,TransactionType.POSITIVE,0,0,1000,2000));
        transactionsList.add(new Transaction(1,TransactionType.POSITIVE,0,0,2000,4000));
    }

    private void setStocksList()
    {
        stocksList.add(new Stock(0,"MemeCoinCEBP1",10000,2,0.1,2));
        stocksList.add(new Stock(1,"MemeCoinCEBP2",20000,4,0.1,2));
    }

    public void setAll()
    {
        setTransactionsList();
        setStocksList();
    }

    public static <T,ID> T getById(List<T> list, ID id, Function<T,ID> idExtractor) // O.O Delegate Methodas???
    {
        for (T item : list)
        {
            if (idExtractor.apply(item).equals(id))
            {
                return item;
            }
        }
        return null;
    }

    @GetMapping("/hello")
    public String hello(){
        String response = "Hello World!";
        return response;
    }

    // ===== Transactions =====

    @GetMapping("/transaction/all")
    public String getAllTransaction(){
        setAll();
        List<Transaction> response = transactionsList;
        return Json.pretty(response);
    }

    @GetMapping("transaction/{transactionID}")
    public String getUserTransaction(@PathVariable Long transactionID){
        setAll();
        Transaction response = getById(transactionsList,transactionID,Transaction::getTransactionId);
        return Json.pretty(response);
    }

    // ===== Stocks =====

    @GetMapping("stock/all")
    public String getAllStock()
    {
        setAll();
        List<Stock> response = stocksList;
        return Json.pretty(response);
    }

    @GetMapping("stock/{stockID}")
    public String getStock(@PathVariable int stockID){
        setAll();
        Stock response = getById(stocksList,stockID,Stock::getStockId);
        return Json.pretty(response);
    }
}
