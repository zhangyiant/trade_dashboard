package org.anteestudio.stock.dashboard.struts2.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.anteestudio.stock.hbm.StockCashTotalHistory;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import javax.persistence.TemporalType;

public class StockCashTotalHistoriesController
    implements ModelDriven<Object> {

    private SessionFactory sessionFactory;
    private List<StockCashTotalHistoryData> model;
    private String period;
    private String symbol;

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        return;
    }

    public void setPeriod(String period) {
        this.period = period;
        return;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        return;
    }
 
    @Action(className="stockCashTotalHistoriesController")
    public String index() {
        Session session = sessionFactory.openSession();
        Instant i = Instant.now();
        Instant t;
        String queryString;

        if (period.equals("all")) {
            t = i.minusSeconds(120*60*60*24*30);
        } else if (period.equals("1month")) {
            t = i.minusSeconds(1*60*60*24*30);
        } else if (period.equals("3month")) {
            t = i.minusSeconds(3*60*60*24*30);
        } else if (period.equals("6month")) {
            t = i.minusSeconds(6*60*60*24*30);
        } else if (period.equals("1year")) {
            t = i.minusSeconds(12*60*60*24*30);
        } else {
            t = i;
        }

        List<StockCashTotalHistory> result;
        if (symbol == null) {
            if (period.equals("all")) {
                queryString = "from StockCashTotalHistory " +
                    "StockCashTotalHistory " +
                    "order by StockCashTotalHistory.datetime desc";
                result = session.
                    createQuery(queryString, StockCashTotalHistory.class).
                    getResultList();
            } else {
                queryString = "from StockCashTotalHistory " +
                    "StockCashTotalHistory " +
                    "where StockCashTotalHistory.datetime > ? " +
                    "order by StockCashTotalHistory.datetime desc";
                result = session.
                    createQuery(queryString, StockCashTotalHistory.class).
                    setParameter(0, t, TemporalType.TIMESTAMP).
                    getResultList();
            }
        } else {
            if (period.equals("all")) {
                queryString = "from StockCashTotalHistory " +
                    "StockCashTotalHistory " +
                    "where StockCashTotalHistory.symbol = ? " +
                    "order by StockCashTotalHistory.datetime desc";
                result = session.
                    createQuery(queryString, StockCashTotalHistory.class).
                    setParameter(0, symbol, StringType.INSTANCE).
                    getResultList();
            } else {
                queryString = "from StockCashTotalHistory " +
                    "StockCashTotalHistory " +
                    "where StockCashTotalHistory.symbol = ? " +
                    " and StockCashTotalHistory.datetime > ? " +
                    "order by StockCashTotalHistory.datetime desc";
                result = session.
                    createQuery(queryString, StockCashTotalHistory.class).
                    setParameter(0, symbol, StringType.INSTANCE).
                    setParameter(1, t, TemporalType.TIMESTAMP).
                    getResultList();
            }
        }
        this.model = new ArrayList<StockCashTotalHistoryData>();
        for (StockCashTotalHistory a: result) {
            StockCashTotalHistoryData d = new StockCashTotalHistoryData();
            d.setId(a.getId());
            d.setSymbol(a.getSymbol());
            d.setTotalValue(a.getTotalValue());
            d.setEpochMilli(a.getDatetime().toEpochMilli());
            this.model.add(d);
        }
        session.close();
        return "index";
    }

    public Object getModel() {
        return this.model;
    }
}
