package by.byport.desktop.services.impl;

import by.byport.desktop.dao.TaskDao;
import by.byport.desktop.entities.Task;
import by.byport.desktop.services.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl extends BaseService<Task> implements TaskService {

    @Autowired
    TaskDao taskDao;

    private static Logger logger = Logger.getLogger(TaskServiceImpl.class);


    @Override
    public List<Task> getAllByEmployee(Long id) {
       List<Task> list = taskDao.getAllByEmployee(id);
        logger.warn("Service method getlAllByEmployee:" + list+ " where id=" + id);
        return list;
    }
}
