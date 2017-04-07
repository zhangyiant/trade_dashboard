package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;


@Entity
@Table(name="stock_info")
public class StockInfo {

    @Id
    @Column(name="symbol")
    private String symbol;

    @Column(name="name")
    private String name;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        return;
    }
    public String getSymbol() {
        return this.symbol;
    }

    public void setName(String name) {
        this.name = name;
        return;
    }
    public String getName() {
        return this.name;
    }
}
