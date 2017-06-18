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
import org.anteestudio.stock.hbm.StockTransaction;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import javax.persistence.TemporalType;

public class StockTransactionsController
    implements ModelDriven<Object> {

    private SessionFactory sessionFactory;
    private List<StockTransactionData> model;
    private String period;
    private String symbol;
    private Boolean sort;

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

    public void setSort(Boolean sort) {
        this.sort = sort;
        return;
    }

    private String addSortString(String queryString) {
        String result;
        if (this.sort) {
            result = queryString +
                " order by StockTransaction.price asc," +
                " StockTransaction.datetime desc";
        } else {
            result = queryString +
                " order by StockTransaction.datetime desc";
        }
        return result;
    }

    @Action(className="stockTransactionsController")
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

        List<StockTransaction> result;
        if (symbol == null) {
            if (period.equals("all")) {
                queryString = "from StockTransaction " +
                    "StockTransaction";
                queryString = this.addSortString(queryString);
                result = session.
                    createQuery(queryString, StockTransaction.class).
                    getResultList();
            } else {
                queryString = "from StockTransaction " +
                    "StockTransaction " +
                    "where StockTransaction.datetime > ?";
                queryString = this.addSortString(queryString);
                result = session.
                    createQuery(queryString, StockTransaction.class).
                    setParameter(0, t, TemporalType.TIMESTAMP).
                    getResultList();
            }
        } else {
            if (period.equals("all")) {
                queryString = "from StockTransaction " +
                    "StockTransaction " +
                    "where StockTransaction.symbol = ?";
                queryString = this.addSortString(queryString);
                result = session.
                    createQuery(queryString, StockTransaction.class).
                    setParameter(0, symbol, StringType.INSTANCE).
                    getResultList();
            } else {
                queryString = "from StockTransaction " +
                    "StockTransaction " +
                    "where StockTransaction.symbol = ? " +
                    " and StockTransaction.datetime > ?";
                queryString = this.addSortString(queryString);
                result = session.
                    createQuery(queryString, StockTransaction.class).
                    setParameter(0, symbol, StringType.INSTANCE).
                    setParameter(1, t, TemporalType.TIMESTAMP).
                    getResultList();
            }
        }
        this.model = new ArrayList<StockTransactionData>();
        for (StockTransaction a: result) {
            StockTransactionData d = new StockTransactionData();
            d.setId(a.getId());
            d.setSymbol(a.getSymbol());
            d.setBuyOrSell(a.getBuyOrSell());
            d.setPrice(a.getPrice());
            d.setQuantity(a.getQuantity());
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
