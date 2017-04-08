package org.anteestudio.stock.dashboard.struts2.rest;


public class StockTransactionData {
    private long id;
    private String symbol;
    private String buyOrSell;
    private long quantity;
    private float price;
    private long epochMilli;

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

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
        return;
    }
    public String getBuyOrSell() {
        return this.buyOrSell;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
        return;
    }
    public long getQuantity() {
        return quantity;
    }

    public void setPrice(float price) {
        this.price = price;
        return;
    }
    public float getPrice() {
        return this.price;
    }

    public void setEpochMilli(long epochMilli) {
        this.epochMilli = epochMilli;
        return;
    }
    public long getEpochMilli() {
        return this.epochMilli;
    }
}
