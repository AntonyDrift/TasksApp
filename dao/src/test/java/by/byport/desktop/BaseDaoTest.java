package by.byport.desktop;

import by.byport.desktop.dao.EmployeeDao;
import by.byport.desktop.dao.TaskDao;
import by.byport.desktop.entities.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/META-INF/test-dao.xml")
public class BaseDaoTest {

    @Autowired
    TaskDao taskDao;
    @Autowired
    EmployeeDao employeeDao;

    public void getInfo(Object expected, Object actual) {
        System.out.println("\nExpected:\n" + expected);
        System.out.println("\nActual:\n" + actual);
    }

    @Test
    public void add() {
        Task expected = new Task("Build Swing App", Timestamp.valueOf("2019-11-19 18:00:00"));
        Task actual = taskDao.add(expected);
        actual = taskDao.get(actual.getId());
        getInfo(expected, actual);
        Assert.assertEquals("Entity isn't persisted", expected, actual);
    }

    @Test
    public void get() {
        Long expected = 6l;
        Task persistent = (Task) taskDao.get(expected);
        System.out.println(persistent != null ? "not null" : "null");
        Long actual = persistent.getId();
        getInfo(expected, actual);
        Assert.assertEquals("Wrong ID", expected, actual);
    }

    @Test
    public void getAll() {
        Task task = new Task("Build Swing App", Timestamp.valueOf("2019-11-19 18:00:00"));
        List<Task> expected = new ArrayList<>();
        expected.add(taskDao.add(task));
        List<Task> actual = taskDao.getAll();
        getInfo(expected, actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Task expected = (Task) taskDao.get(5l);
        expected.setConfirmationDate(new Timestamp(System.currentTimeMillis()));
        expected = taskDao.update(expected);
        Task actual = (Task) taskDao.get(5l);
        getInfo(expected, actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        List<Task> list = taskDao.getAll();
        int actual = list.size();
        if (list.size() > 0) {
            Task persistent = list.get(0);
            taskDao.delete(persistent.getId());
            int expected = taskDao.getAll().size();
            getInfo(expected, actual);
            Assert.assertNotSame(expected, actual);
        }
    }


}
