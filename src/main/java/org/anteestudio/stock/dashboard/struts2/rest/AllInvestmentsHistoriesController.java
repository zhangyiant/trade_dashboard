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
import org.anteestudio.stock.hbm.AllInvestmentsHistory;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.TemporalType;

public class AllInvestmentsHistoriesController
    implements ModelDriven<Object> {

    private SessionFactory sessionFactory;
    private List<AllInvestmentsHistoryData> model;
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
      
    @Action(className="allInvestmentsHistoriesController")
    public String index() {
        Session session = sessionFactory.openSession();
        Instant i = Instant.now();
        Instant t;
        String queryString;

        List<AllInvestmentsHistory> result;
        if (period == null) {
            queryString = "from AllInvestmentsHistory AllInvestmentsHistory " +
                "order by AllInvestmentsHistory.datetime desc";
            result = session.
                createQuery(queryString,
                            AllInvestmentsHistory.class).getResultList();
        } else if (period.equals("all")) {
            queryString = "from AllInvestmentsHistory AllInvestmentsHistory " +
                "order by AllInvestmentsHistory.datetime desc";
            result = session.
                createQuery(queryString, AllInvestmentsHistory.class).
                getResultList();
        } else {
            if (period.equals("1month")) {
                t = i.minusSeconds(60*60*24*30);
            } else if (period.equals("3month")) {
                t = i.minusSeconds(3*60*60*24*30);
            } else if (period.equals("6month")) {
                t = i.minusSeconds(6*60*60*24*30);
            } else if (period.equals("1year")) {
                t = i.minusSeconds(12*60*60*24*30);
            } else {
                t = i;
            }
            result =
                session.
                createQuery("from AllInvestmentsHistory AllInvestmentsHistory " +
                            "where AllInvestmentsHistory.datetime > ? " + 
                            "order by AllInvestmentsHistory.datetime desc",
                            AllInvestmentsHistory.class).
                setParameter(0, t, TemporalType.TIMESTAMP).
                getResultList();
        }

        this.model = new ArrayList<AllInvestmentsHistoryData>();
        for (AllInvestmentsHistory a: result) {
            AllInvestmentsHistoryData d = new AllInvestmentsHistoryData();
            d.setId(a.getId());
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
