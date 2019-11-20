package by.byport.desktop.dao;

import by.byport.desktop.entities.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task> {

    List<Task> getAllByEmployee(Long id);

}
