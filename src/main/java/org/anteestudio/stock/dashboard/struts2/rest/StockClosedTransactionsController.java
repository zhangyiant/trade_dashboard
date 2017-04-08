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

        List<StockClosedTransaction> result;
        String queryString = "from StockClosedTransaction " +
            "StockClosedTransaction " +
            "order by StockClosedTransaction.sellDatetime " +
            "desc";
        result = session.
            createQuery(queryString,
                        StockClosedTransaction.class).
            getResultList();

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
