package by.byport.desktop.services;

import by.byport.desktop.dao.GenericDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface GenericService<T> {

    T add(T t);

    T get(Serializable id);

    List<T> getAll();

    T update(T t);

    void delete(long id);
}
