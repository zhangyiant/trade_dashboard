package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="stock_cash")
public class StockCash {

    @Id
    @Column(name="symbol")
    private String symbol;

    @Column(name="amount")
    private float amount;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        return;
    }
    public String getSymbol() {
        return this.symbol;
    }

    public void setAmount(float amount) {
        this.amount = amount;
        return;
    }
    public float getAmount() {
        return this.amount;
    }
}
