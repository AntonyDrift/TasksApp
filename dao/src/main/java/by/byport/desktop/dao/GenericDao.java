package by.byport.desktop.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {

    T add(T t);
    T get(Serializable id);
    List<T> getAll();
    T update(T t);
    void delete(long id);

}
