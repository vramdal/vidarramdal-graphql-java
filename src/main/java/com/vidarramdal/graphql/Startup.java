package com.vidarramdal.graphql;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class Startup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database database = Database.getInstance();
        database.init();
        database.shutdown();
        PersistenceUtil.buildEntityManagerFactory();
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Query query = entityManager.createQuery("from Handlekurv ");
        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println("o = " + o);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
