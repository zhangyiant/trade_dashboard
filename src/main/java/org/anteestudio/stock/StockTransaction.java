package org.anteestudio.stock;

import java.time.Instant;

public class StockTransaction {
    private Long transId;
    private String symbol;
    private String buyOrSell;
    private Long quantity;
    private float price;
    private Instant date;

    public StockTransaction() {
    }

    public Long getTransId() {
	return transId;
    }
    public void setTransId(Long transId) {
	this.transId = transId;
    }

    public String getSymbol() {
	return symbol;
    }
    public void setSymbol(String symbol) {
	this.symbol = symbol;
    }
    public String getBuyOrSell() {
	return buyOrSell;
    }
    public void setBuyOrSell(String buyOrSell) {
	this.buyOrSell = buyOrSell;
	return;
    }
    public Long getQuantity() {
	return quantity;
    }
    public void setQuantity(Long quantity) {
	this.quantity = quantity;
	return;
    }
    public float getPrice() {
	return price;
    }
    public void setPrice(float price) {
	this.price = price;
	return;
    }
    public Instant getDate() {
	return date;
    }
    public void setDate(Instant date) {
	this.date = date;
	return;
    }
}
