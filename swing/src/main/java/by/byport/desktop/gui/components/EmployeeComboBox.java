package by.byport.desktop.gui.components;

import by.byport.desktop.dao.impl.TaskDaoImpl;
import by.byport.desktop.entities.Employee;
import by.byport.desktop.services.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class EmployeeComboBox {

    private static Logger logger = Logger.getLogger(EmployeeComboBox.class);

    @Autowired
    private EmployeeService employeeService;
    private JComboBox comboBox;

    public EmployeeComboBox() {
        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(150, 23));
        logger.warn("initialise employeeComboBox:" + comboBox);
    }

    public Long getEmployeeId() {
        Employee item = (Employee) comboBox.getSelectedItem();
        logger.warn("get employeeId=" + item.getId());
        return item.getId();
    }

    public JComboBox getComboBox() {
        logger.warn("get instance comboBox:" + comboBox);
        return comboBox;
    }

    public void addEmployees() {
        List<Employee> list = employeeService.getAll();
        for (Employee item : list) {
            comboBox.addItem(item);
        }
        logger.warn("add employees in comboBox:" + list);
    }

}