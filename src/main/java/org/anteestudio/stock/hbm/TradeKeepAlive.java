package org.anteestudio.stock.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table(name="trade_keep_alive")
public class TradeKeepAlive {

    @Id
    @Column(name="keep_alive_id")
    private Long id;

    @Column(name="app_name")
    private String appName;

    @Column(name="refresh_time")
    private Instant refreshTime;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Instant getRefreshTime() {
        return this.refreshTime;
    }
    public void setRefreshTime(Instant refreshTime) {
        this.refreshTime = refreshTime;
        return;
    }
}
