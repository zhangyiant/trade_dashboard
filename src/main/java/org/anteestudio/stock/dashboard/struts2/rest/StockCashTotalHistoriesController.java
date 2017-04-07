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
import javax.persistence.TemporalType;

public class StockCashTotalHistoriesController
    implements ModelDriven<Object> {

    private SessionFactory sessionFactory;
    private List<StockCashTotalHistoryData> model;
    private String period;

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
      
    @Action(className="stockCashTotalHistoriesController")
    public String index() {
        Session session = sessionFactory.openSession();
        Instant i = Instant.now();
        Instant t;
        String queryString;

        List<StockCashTotalHistory> result;
        queryString = "from StockCashTotalHistory StockCashTotalHistory " +
            "order by StockCashTotalHistory.datetime desc";
        result = (List<StockCashTotalHistory>)session.
            createQuery(queryString).list();

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