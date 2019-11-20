package by.byport.desktop.gui;

import by.byport.desktop.entities.Employee;
import by.byport.desktop.gui.components.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleForm extends JFrame {


    private static Logger logger = Logger.getLogger(SimpleForm.class);

    private ApplicationContext context;
    private TaskTable taskTableBean;
    private PlanTable planTableBean;
    private EmployeeComboBox employeeComboBoxBean;
    private MonthComboBox monthComboBoxBean;

    private Font boldFont;
    private Font plainFont;

    private JPanel headerPanel;
    private JPanel taskPanel;
    private JPanel planPanel;
    private JPanel headerPlanPanel;

    private JTabbedPane tabbedPane;

    private JLabel employeeLabel;
    private JLabel monthLabel;

    private JComboBox employeeComboBox;
    private JComboBox monthComboBox;

    {//spring context + init beans
        context = new ClassPathXmlApplicationContext("/META-INF/swing.xml");
        employeeComboBoxBean = (EmployeeComboBox) context.getBean("employeeComboBox");
        taskTableBean = (TaskTable) context.getBean("taskTable");
        monthComboBoxBean = (MonthComboBox) context.getBean("monthComboBox");
        planTableBean = (PlanTable) context.getBean("planTable");

        boldFont = new Font("Arial", Font.BOLD, 14);
        plainFont= new Font("Arial", Font.PLAIN, 14);
    }

    {
        //header block: contains "employee" label and "employee" comboBox
        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        employeeLabel = new JLabel("Сотрудник:");
        employeeLabel.setFont(boldFont);
        employeeLabel.setPreferredSize(new Dimension(120, 23));
        employeeComboBoxBean.addEmployees();
        employeeComboBox = employeeComboBoxBean.getComboBox();
        employeeComboBox.setFont(boldFont);

        headerPanel.add(employeeLabel);
        headerPanel.add(employeeComboBox);
    }

    {//Center block: contains "tabbedPane".
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(boldFont);

        {//tab "Task" with table
            taskPanel = new JPanel(new GridLayout(1, 1));

            Long employeeId = employeeComboBoxBean.getEmployeeId();
            taskTableBean.setEmployeeId(employeeId);
            taskTableBean.addRow();
            taskTableBean.getTable().getTableHeader().setFont(plainFont);
            taskTableBean.getTable().setFont(plainFont);
            taskPanel.add(taskTableBean.getTaskTable());
        }

        {//tab "Plan" with header and table
            planPanel = new JPanel(new GridLayout(1, 1));
            planPanel.setLayout(new BoxLayout(planPanel, BoxLayout.Y_AXIS));

            {//Header in a tab "Plan". Contains "Month" label and comboBox
                headerPlanPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                //Set Dimension: width = screenSize.width, height=20;
                headerPlanPanel.setMaximumSize(new Dimension
                        (Toolkit.getDefaultToolkit().getScreenSize().width, 20));

                {//initial "Month" label and "Month" comboBox
                    monthLabel = new JLabel("Месяц");
                    monthLabel.setFont(boldFont);
                    monthLabel.setPreferredSize(new Dimension(120,23));
                    monthComboBoxBean.addMonth();
                    monthComboBox = monthComboBoxBean.getComboBox();
                    monthComboBox.setFont(boldFont);

                    headerPlanPanel.add(monthLabel);
                    headerPlanPanel.add(monthComboBox);
                }
                planPanel.add(headerPlanPanel);
            }

            {//Body on tab "Plan". Contain table
                planTableBean.getHeader();
                planTableBean.addRow(1, 31, taskTableBean.getList());
                planTableBean.getTable().getTableHeader().setFont(plainFont);
                planTableBean.getTable().setFont(plainFont);

                planPanel.add(planTableBean.getPlanTable());
            }
        }

        tabbedPane.addTab("Задачи", taskPanel);
        tabbedPane.addTab("План", planPanel);
    }

    SimpleForm(String title) {
        super(title);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        //fix position on center when app first applied
        this.setLocationRelativeTo(null);

        this.add(headerPanel, BorderLayout.NORTH);
        //check listener methods
        this.employeeListener();
        this.monthListener();
        this.fullScreenListener();

        this.add(tabbedPane, BorderLayout.CENTER);
        logger.warn("init application" + this.toString());
    }

    //Dynamic recording rows on tables
    public void employeeListener() {

        employeeComboBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                Employee item = (Employee) itemEvent.getItem();

                taskTableBean.setEmployeeId(item.getId());
                taskTableBean.addRow();
                planTableBean.getHeader();
                planTableBean.addRow(1, 31, taskTableBean.getList());
                logger.warn("install listener from employees comboBox");
            }
        });
    }
    public void fullScreenListener() {
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {

               int currentWidth = componentEvent.getComponent().getWidth();
               taskTableBean.changeWidthColumn(currentWidth);
            }
            @Override
            public void componentMoved(ComponentEvent componentEvent) {
            }
            @Override
            public void componentShown(ComponentEvent componentEvent) {
            }
            @Override
            public void componentHidden(ComponentEvent componentEvent) {
                logger.warn("install listener from window resolution");
            }
        });

    }

    public void monthListener() {
        monthComboBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                SimpleMonth month = (SimpleMonth) itemEvent.getItem();
                Boolean isLeapYear = month.hasLeapYear(taskTableBean.getList());
                Integer daysInMonth = month.getDays(month.getNumber(), isLeapYear);

                planTableBean.setDays(daysInMonth);
                planTableBean.getHeader();
                planTableBean.addRow(month.getNumber(), daysInMonth, taskTableBean.getList());
                logger.warn("install listener from monthComboBox");
            }
        });
    }

}