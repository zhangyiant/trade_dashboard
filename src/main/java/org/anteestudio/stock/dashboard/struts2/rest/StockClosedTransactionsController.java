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
import org.anteestudio.stock.hbm.StockClosedTransaction;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import javax.persistence.TemporalType;

public class StockClosedTransactionsController
    implements ModelDriven<Object> {

    private SessionFactory sessionFactory;
    private List<StockClosedTransactionData> model;
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

    @Action(className="stockClosedTransactionsController")
    public String index() {
        Session session = sessionFactory.openSession();
        Instant i = Instant.now();
        Instant t = null;
        String queryString;

        if (period != null) {
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
        } else {
            setPeriod("all");
        }

        List<StockClosedTransaction> result;
        if (symbol == null) {
            if (period.equals("all")) {
                queryString = "from StockClosedTransaction " +
                    "StockClosedTransaction " +
                    "order by StockClosedTransaction.sellDatetime desc";
                result = session.
                    createQuery(queryString, StockClosedTransaction.class).
                    getResultList();
            } else {
                queryString = "from StockClosedTransaction " +
                    "StockClosedTransaction " +
                    "where StockClosedTransaction.sellDatetime > ? " +
                    "order by StockClosedTransaction.sellDatetime desc";
                result = session.
                    createQuery(queryString, StockClosedTransaction.class).
                    setParameter(0, t, TemporalType.TIMESTAMP).
                    getResultList();
            }
        } else {
            if (period.equals("all")) {
                queryString = "from StockClosedTransaction " +
                    "StockClosedTransaction " +
                    "where StockClosedTransaction.symbol = ? " +
                    "order by StockClosedTransaction.sellDatetime desc";
                result = session.
                    createQuery(queryString, StockClosedTransaction.class).
                    setParameter(0, symbol, StringType.INSTANCE).
                    getResultList();
            } else {
                queryString = "from StockClosedTransaction " +
                    "StockClosedTransaction " +
                    "where StockClosedTransaction.symbol = ? " +
                    " and StockClosedTransaction.sellDatetime > ? " +
                    "order by StockClosedTransaction.sellDatetime desc";
                result = session.
                    createQuery(queryString, StockClosedTransaction.class).
                    setParameter(0, symbol, StringType.INSTANCE).
                    setParameter(1, t, TemporalType.TIMESTAMP).
                    getResultList();
            }
        }

        this.model =
            new ArrayList<StockClosedTransactionData>();
        for (StockClosedTransaction a: result) {
            StockClosedTransactionData d =
                new StockClosedTransactionData();
            d.setId(a.getId());
            d.setSymbol(a.getSymbol());
            d.setBuyPrice(a.getBuyPrice());
            d.setBuyEpochMilli(a.getBuyDatetime().
                              toEpochMilli());
            d.setSellPrice(a.getSellPrice());
            d.setSellEpochMilli(a.getSellDatetime().
                                toEpochMilli());
            d.setQuantity(a.getQuantity());
            this.model.add(d);
        }
        session.close();
        return "index";
    }

    public Object getModel() {
        return this.model;
    }
}
