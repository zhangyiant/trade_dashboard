package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table(name="stock_transaction")
public class StockTransaction {

    @Id
    @Column(name="trans_id")
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="buy_or_sell")
    private String buyOrSell;

    @Column(name="quantity")
    private Long quantity;

    @Column(name="price")
    private float price;

    @Column(name="date")
    private Instant datetime;

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
    public Instant getDatetime() {
        return this.datetime;
    }
    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
        return;
    }
}
