package org.anteestudio.stock;

import java.sql.Date;

public class StockClosedTransaction {
    private Long transId;
    private String symbol;
    private float buyPrice;
    private Date buyDate;
    private float sellPrice;
    private Date sellDate;
    private Long quantity;

    public StockClosedTransaction() {
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
    public float getBuyPrice() {
	return buyPrice;
    }
    public void setBuyPrice(float buyPrice) {
	this.buyPrice = buyPrice;
    }
    public Date getBuyDate() {
	return buyDate;
    }
    public void setBuyDate(Date buyDate) {
	this.buyDate = buyDate;
	return;
    }
    public float getSellPrice() {
	return sellPrice;
    }
    public void setSellPrice(float sellPrice) {
	this.sellPrice = sellPrice;
	return;
    }
    public Date getSellDate() {
	return sellDate;
    }
    public void setSellDate(Date sellDate) {
	this.sellDate = sellDate;
	return;
    }
    public Long getQuantity() {
	return quantity;
    }
    public void setQuantity(Long quantity) {
	this.quantity = quantity;
	return;
    }
}
