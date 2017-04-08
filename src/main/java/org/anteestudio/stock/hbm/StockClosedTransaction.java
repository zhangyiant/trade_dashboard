package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table(name="stock_closed_transaction")
public class StockClosedTransaction {

    @Id
    @Column(name="trans_id")
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="buy_price")
    private float buyPrice;

    @Column(name="buy_date")
    private Instant buyDatetime;

    @Column(name="sell_price")
    private float sellPrice;

    @Column(name="sell_date")
    private Instant sellDatetime;

    @Column(name="quantity")
    private Long quantity;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return;
    }
    public Instant getBuyDatetime() {
        return this.buyDatetime;
    }
    public void setBuyDatetime(Instant buyDatetime) {
        this.buyDatetime = buyDatetime;
        return;
    }

    public float getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
        return;
    }
    public Instant getSellDatetime() {
        return this.sellDatetime;
    }
    public void setSellDatetime(Instant sellDatetime) {
        this.sellDatetime = sellDatetime;
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
