package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;


@Entity
@Table(name="stock_cash_total_history_value")
public class StockCashTotalHistory {

    @Id
    @Column(name="history_value_id")
    private Long id;

    @Column(name="symbol")
    private String symbol;


    @Column(name="date")
    private Instant datetime;

    @Column(name="total_value")
    private float totalValue;

    public void setId(Long id) {
        this.id = id;
        return;
    }

    public Long getId() {
        return this.id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        return;
    }
    public String getSymbol() {
        return this.symbol;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
        return;
    }
    public Instant getDatetime() {
        return this.datetime;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
        return;
    }
    public float getTotalValue() {
        return this.totalValue;
    }
}
