package by.byport.desktop.dao.impl;

import by.byport.desktop.dao.TaskDao;
import by.byport.desktop.entities.Task;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl extends BaseDao<Task> implements TaskDao {

    private static Logger logger = Logger.getLogger(TaskDaoImpl.class);


    public TaskDaoImpl() {
        super();
        clazz = Task.class;
    }

    @Override
    public List<Task> getAllByEmployee(Long id) {
        List<Task> list = getEm()
                .createQuery("SELECT T FROM Task T INNER JOIN T.employees E WHERE E.id=" + id)
                .getResultList();
        logger.warn("Get list:" + list + " where employeeID=" + id);
        return list;
    }

}
