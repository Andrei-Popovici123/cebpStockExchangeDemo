package vcarb.stockexchange.server.dto;

import vcarb.stockexchange.server.entities.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class StockDTO {
    public String name;

    public int amount;

    public double price;

    public double apreCoef = 0.1;
}
