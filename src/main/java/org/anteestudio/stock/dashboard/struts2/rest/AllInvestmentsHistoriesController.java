package org.anteestudio.stock.dashboard.struts2.rest;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.anteestudio.stock.hbm.AllInvestmentsHistory;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AllInvestmentsHistoriesController
    implements ModelDriven<Object> {
    
    private SessionFactory sessionFactory;
    private List<AllInvestmentsHistory> model;
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        return;
    }

    public void setId(String id) {
        return;
    }

    @Action(className="allInvestmentsHistoriesController")
    public String show() {
        //this.model = new ArrayList<AllInvestmentsHistory>();
        //AllInvestmentsHistory t = new AllInvestmentsHistory();
        //this.model.add(t);
        Session session = sessionFactory.openSession();
        List<AllInvestmentsHistory> result =
            (List<AllInvestmentsHistory>)session.createQuery("from AllInvestmentsHistory").list();
        model = result;
        session.close();
        return "show";
    }

    public Object getModel() {
        return this.model;
    }
}
