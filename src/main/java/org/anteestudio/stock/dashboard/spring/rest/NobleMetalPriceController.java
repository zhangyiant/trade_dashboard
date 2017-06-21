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

import org.anteestudio.stock.hbm.NobleMetalPrice;

@RestController
public class NobleMetalPriceController {

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RequestMapping("/rest/noble-metal-price")
    public NobleMetalPrice get(@RequestParam(value = "symbol") String symbol) {
        Session session = sessionFactory.openSession();
        String queryString;

        System.out.println(symbol);
        List<NobleMetalPrice> result;
        queryString = "from NobleMetalPrice NobleMetalPrice " +
            "where NobleMetalPrice.symbol = ? " +
            "order by NobleMetalPrice.updateDatetime";
        result = session.
            createQuery(queryString, NobleMetalPrice.class).
            setParameter(0, symbol, StringType.INSTANCE).
            setMaxResults(1).
            getResultList();

        NobleMetalPrice price;
        if (result.isEmpty()) {
            price = null;
        } else {
            price = result.get(0);
        }
        session.close();

        return price;
    }
}
