package by.byport.desktop.services.impl;

import by.byport.desktop.dao.GenericDao;
import by.byport.desktop.dao.impl.BaseDao;
import by.byport.desktop.services.GenericService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class BaseService<T> implements GenericService<T> {

    private static Logger logger = Logger.getLogger(BaseService.class);

    @Autowired
    private GenericDao<T> baseDao;

    @Override
    public T add(T t) {
        baseDao.add(t);
        logger.warn("Service method add:" + t);
        return t;
    }

    @Override
    public T get(Serializable id) {
        T t = baseDao.get(id);
        logger.warn("Service method get:" + t + "where id=" + id);
        return t;
    }

    @Override
    public List<T> getAll() {
        List<T> list = baseDao.getAll();
        logger.warn("Service method getAll:" + list);
        return list;
    }

    @Override
    public T update(T t) {
        baseDao.update(t);
        logger.warn("Service method update:" + t);
        return t;
    }

    @Override
    public void delete(long id) {
        baseDao.delete(id);
        logger.warn("Service method delete:" + "where id=" + id);
    }
}
