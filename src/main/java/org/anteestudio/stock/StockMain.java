package org.anteestudio.stock;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class StockMain {
    public static void main(String[] args) {
	StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	Session session = sessionFactory.openSession();
	List result = session.createQuery("from StockClosedTransaction").list();

	for ( StockClosedTransaction s: (List<StockClosedTransaction>) result) {
	    System.out.println(s.getSymbol());
	}

	session.close();
	sessionFactory.close();
    }
}
