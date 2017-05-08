package org.anteestudio.stock.dashboard.spring.rest;

import java.util.List;
import java.time.Instant;
import java.time.Duration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import org.anteestudio.stock.hbm.TradeKeepAlive;

@RestController
public class TradeKeepAliveController {

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    class KeepAliveStatus {
        boolean isRunning;

        public boolean getIsRunning() {
            return this.isRunning;
        }
        public void setIsRunning(boolean isRunning) {
            this.isRunning = isRunning;
            return;
        }
    }
    @RequestMapping("/rest/trade-keep-alive/{appName}")
    public KeepAliveStatus get(@PathVariable String appName) {
        Session session = sessionFactory.openSession();
        KeepAliveStatus result = new KeepAliveStatus();
        String queryString;

        List<TradeKeepAlive> keepAliveList = session.
            createQuery("from TradeKeepAlive TradeKeepAlive " +
                            "where TradeKeepAlive.appName = ?",
                            TradeKeepAlive.class).
            setParameter(0, appName, StringType.INSTANCE).
            getResultList();

        if (keepAliveList.isEmpty()) {
            result.setIsRunning(false);
        } else {
            TradeKeepAlive a = keepAliveList.get(0);
            Instant refreshTime = a.getRefreshTime();
            Instant n = Instant.now();
            Duration d = Duration.between(refreshTime, n);
            long m = d.toMinutes();
            if (m > 10) {
                result.setIsRunning(false);
            } else {
                result.setIsRunning(true);
            }
        }

        session.close();

        return result;
    }
}
