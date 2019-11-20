package by.byport.desktop.services.impl;

import by.byport.desktop.dao.EmployeeDao;
import by.byport.desktop.dao.impl.EmployeeDaoImpl;
import by.byport.desktop.entities.Employee;
import by.byport.desktop.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class EmployeeServiceImpl extends BaseService<Employee> implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;
}
