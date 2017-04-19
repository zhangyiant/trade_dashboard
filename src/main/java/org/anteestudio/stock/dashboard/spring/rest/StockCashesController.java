package org.anteestudio.stock.dashboard.spring.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import org.anteestudio.stock.hbm.StockCash;

@RestController
public class StockCashesController {

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RequestMapping("/rest/stock-cashes")
    public List<StockCash> list() {
        Session session = sessionFactory.openSession();
        String queryString;

        List<StockCash> result;
        queryString = "from StockCash StockCash order by StockCash.symbol";
        result = session.
            createQuery(queryString, StockCash.class).
            getResultList();

        session.close();

        return result;
    }
}
