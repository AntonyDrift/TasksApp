package by.byport.desktop;

import static org.junit.Assert.assertTrue;

import by.byport.desktop.entities.Employee;
import by.byport.desktop.entities.Task;
import by.byport.desktop.services.EmployeeService;
import by.byport.desktop.services.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/services.xml")
public class BaseServiceTest {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    TaskService taskService;

    Employee emp1 = new Employee("Zhurkov A.O.");
    Employee emp2 = new Employee("Kolobkova E.L");
    Employee emp3 = new Employee("Ivanov I.I");

    Task task1 = new Task("december first task", Timestamp.valueOf("2019-12-10 09:00:00"));
    Task task2 = new Task("december second task", Timestamp.valueOf("2019-12-01 09:00:00"));
    Task task3 = new Task("Zhurkov task", Timestamp.valueOf("2019-11-27 09:00:00"));

    @Test
//    @Rollback(false)
    public void run(){
        employeeService.add(emp1);
        employeeService.add(emp2);
        employeeService.add(emp3);

        task1.setStartDate(Timestamp.valueOf("2019-10-20 09:00:00"));
        taskService.add(task1);
        taskService.add(task2);
        taskService.add(task3);
    }


    @Test
    @Transactional
//    @Rollback(false)
    public void foo() {

        Employee zhurkov = employeeService.get(21l);
        Employee kolobkova = employeeService.get(22l);
        Employee ivanov = employeeService.get(23l);

        Task decFirst = taskService.get(24l);
        Task decSec = taskService.get(25l);
        Task zhurTask = taskService.get(26l);

        zhurkov.getTasks().add(decFirst);
        zhurkov.getTasks().add(decSec);
        zhurkov.getTasks().add(zhurTask);

        employeeService.update(zhurkov);

        kolobkova.getTasks().add(decFirst);
        kolobkova.getTasks().add(decSec);

        employeeService.update(kolobkova);

        ivanov.getTasks().add(decFirst);
        ivanov.getTasks().add(decSec);

        employeeService.update(ivanov);

        System.out.println("\n"+decFirst.getEmployees()+"\n");
        System.out.println("\n"+decSec.getEmployees()+"\n");
        System.out.println("\n"+zhurTask.getEmployees()+"\n");
        }

    @Test
    public void add() {
        Task task = new Task("first task", new Timestamp(System.currentTimeMillis()));
        Task task2 = new Task("second task", new Timestamp(System.currentTimeMillis()));
        task2.setStatusTask(true);
        taskService.add(task);
        taskService.add(task2);
    }

    @Test
    public void getAll() {

        List<Task> list = taskService.getAll();
        System.out.println(list);
    }
}
