package org.anteestudio.stock.dashboard.struts2.rest;


public class StockClosedTransactionData {
    private long id;
    private String symbol;
    private float buyPrice;
    private long buyEpochMilli;
    private float sellPrice;
    private long sellEpochMilli;
    private long quantity;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
        return;
    }
    public String getSymbol() {
        return this.symbol;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
        return;
    }
    public float getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyEpochMilli(long buyEpochMilli) {
        this.buyEpochMilli = buyEpochMilli;
        return;
    }
    public long getBuyEpochMilli() {
        return this.buyEpochMilli;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
        return;
    }
    public float getSellPrice() {
        return this.sellPrice;
    }

    public void setSellEpochMilli(long sellEpochMilli) {
        this.sellEpochMilli = sellEpochMilli;
        return;
    }
    public long getSellEpochMilli() {
        return this.sellEpochMilli;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
        return;
    }
    public long getQuantity() {
        return quantity;
    }

}
