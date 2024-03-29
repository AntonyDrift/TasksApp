package by.byport.desktop.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class HibernateUtil {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("by.byport.desktop");
    }
    public static EntityManager getEntityManager() {
       return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
    public static void close() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
