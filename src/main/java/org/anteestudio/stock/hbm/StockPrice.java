package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table(name="stock_price")
public class StockPrice {

    @Id
    @Column(name="stock_price_id")
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="price")
    private float price;

    @Column(name="update_datetime")
    private Instant updateDatetime;

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

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
        return;
    }

    public Instant getUpdateDatetime() {
        return this.updateDatetime;
    }
    public void setUpdateDatetime(Instant datetime) {
        this.updateDatetime = datetime;
        return;
    }
}
