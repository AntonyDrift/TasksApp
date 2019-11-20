package by.byport.desktop.dao.impl;

import by.byport.desktop.dao.GenericDao;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

import java.util.List;

@Repository
public class    BaseDao<T> implements GenericDao<T> {

    private static Logger logger = Logger.getLogger(BaseDao.class);

    protected Class<T> clazz;

    @PersistenceContext
    @Getter
    private EntityManager em;

    @Override
    public T add(T t) {
        em.persist(t);
        logger.warn("Add " + t);
        return t;
    }

    @Override
    public T get(Serializable id) {
        T t = em.find(clazz, id);
        logger.warn("Get: " + t + " where id=" + id);
        return t;
    }

    @Override
    public List<T> getAll() {
        List<T> list = em
                .createQuery("FROM " + clazz.getSimpleName())
                .getResultList();
        logger.warn("Get list: " + list);
        return list;
    }


    @Override
    public T update(T t) {
        em.merge(t);
        logger.warn("Update: " + t);
        return t;
    }

    @Override
    public void delete(long id) {
        T t = em.find(clazz, id);
        em.remove(t);
        logger.warn("Delete: " + t + " where id=" + id);
    }
}
