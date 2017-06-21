package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table(name="noble_metal_price")
public class NobleMetalPrice {

    @Id
    @Column(name="noble_metal_price_id")
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="buy_price")
    private float buyPrice;
    @Column(name="sell_price")
    private float sellPrice;
    @Column(name="middle_price")
    private float middlePrice;
    @Column(name="highest_middle_price")
    private float highestMiddlePrice;
    @Column(name="lowest_middle_price")
    private float lowestMiddlePrice;

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

    public float getBuyPrice() {
        return buyPrice;
    }
    public void setBuyPrice(float price) {
        this.buyPrice = price;
        return;
    }
    public float getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(float price) {
        this.sellPrice = price;
        return;
    }
    public float getMiddlePrice() {
        return middlePrice;
    }
    public void setMiddlePrice(float price) {
        this.middlePrice = price;
        return;
    }
    public float getHighestMiddlePrice() {
        return highestMiddlePrice;
    }
    public void setHighestMiddlePrice(float price) {
        this.highestMiddlePrice = price;
        return;
    }
    public float getLowestMiddlePrice() {
        return lowestMiddlePrice;
    }
    public void setLowestMiddlePrice(float price) {
        this.lowestMiddlePrice = price;
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
