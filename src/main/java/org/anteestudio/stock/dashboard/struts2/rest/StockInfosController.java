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
import org.anteestudio.stock.hbm.StockInfo;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StockInfosController
    implements ModelDriven<Object> {

    private SessionFactory sessionFactory;
    private List<StockInfo> model;

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        return;
    }

    @Action(className="stockInfosController")
    public String index() {
        Session session = sessionFactory.openSession();
        String queryString;

        List<StockInfo> result;
        queryString = "from StockInfo StockInfo " +
                "order by StockInfo.name desc";
        result = session.
            createQuery(queryString, StockInfo.class).getResultList();

        this.model = result;
        session.close();
        return "index";
    }

    public Object getModel() {
        return this.model;
    }
}
