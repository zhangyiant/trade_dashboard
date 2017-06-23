package org.anteestudio.stock.dashboard.spring.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.type.StringType;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import org.anteestudio.stock.hbm.StockPrice;

@RestController
public class StockPriceController {

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RequestMapping("/rest/stock-price")
    public StockPrice get(@RequestParam(value = "symbol") String symbol) {
        Session session = sessionFactory.openSession();
        String queryString;

        System.out.println(symbol);
        List<StockPrice> result;
        queryString = "from StockPrice StockPrice " +
            "where StockPrice.symbol = ? " +
            "order by StockPrice.updateDatetime desc";
        result = session.
            createQuery(queryString, StockPrice.class).
            setParameter(0, symbol, StringType.INSTANCE).
            setMaxResults(1).
            getResultList();

        StockPrice price;
        if (result.isEmpty()) {
            price = null;
        } else {
            price = result.get(0);
        }
        session.close();

        return price;
    }
}
