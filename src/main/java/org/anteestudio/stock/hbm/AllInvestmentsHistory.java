package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table( name = "all_investments_history")
public class AllInvestmentsHistory {

    @Id
    @Column( name = "history_id")
    private Long id;

    @Column( name = "date")
    private Instant datetime;

    @Column( name = "total_value")
    private float totalValue;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
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
