package by.byport.desktop.services;

import by.byport.desktop.entities.Task;
import by.byport.desktop.services.GenericService;

import java.util.List;

public interface TaskService extends GenericService<Task> {

    List<Task> getAllByEmployee(Long id);

}
