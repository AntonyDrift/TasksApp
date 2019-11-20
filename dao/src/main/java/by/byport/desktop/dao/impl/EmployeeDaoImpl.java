package by.byport.desktop.dao.impl;

import by.byport.desktop.dao.EmployeeDao;
import by.byport.desktop.dao.GenericDao;
import by.byport.desktop.entities.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends BaseDao<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super();
        clazz = Employee.class;
    }
}
