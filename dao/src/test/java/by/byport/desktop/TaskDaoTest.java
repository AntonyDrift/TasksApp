package by.byport.desktop;


import by.byport.desktop.dao.TaskDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/META-INF/test-dao.xml")
public class TaskDaoTest {

    @Autowired
    TaskDao taskDao;

    @Test
    public void getById(){
        System.out.println(taskDao.getAllByEmployee(22l));
    }

}
