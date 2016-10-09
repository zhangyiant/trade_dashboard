package org.anteestudio.stock;

import java.util.Date;

public class StockClosedTransaction {
    private Long transId;
    private String symbol;
    private float buyPrice;
    //private Date buyDate;
    //private float sellPrice;
    //private Date sellDate;
    //private Long quantity;

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
}
