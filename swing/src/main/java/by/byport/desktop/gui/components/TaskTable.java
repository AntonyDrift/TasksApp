package by.byport.desktop.gui.components;

import by.byport.desktop.entities.Task;
import by.byport.desktop.services.TaskService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class TaskTable {

    private static Logger logger = Logger.getLogger(TaskTable.class);

    @Autowired
    TaskService taskService;

    @Getter
    @Setter
    private Long employeeId;

    @Getter
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public TaskTable() {
        Object[] tableHeader = new String[]{"Выполнена", "Задача", "Дата начала", "Дата окончания", "Дата выполнения"};
        tableModel = new DefaultTableModel(tableHeader, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(23);
        scrollPane = new JScrollPane(table);
        logger.warn("init taskTable:" + table);
    }

    public void changeWidthColumn(int currentWidth) {

        int columnCount = table.getColumnModel().getColumnCount();
        int colHeaderCount = table.getTableHeader().getColumnModel().getColumnCount();

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);


        for (int i = 1; i < columnCount; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth((currentWidth - 80) / (colHeaderCount - 1));
        }
        logger.warn("changing width column by resolution.width"+ currentWidth);

    }

    public JScrollPane getTaskTable() {
        logger.warn("get taskTable with scrollPane:" + scrollPane);
        return this.scrollPane;
    }

    public List<Task> getList() {
        logger.warn("get taskList for an employee.Id=" + employeeId);
        return taskService.getAllByEmployee(employeeId);
    }


    public void addRow() {

        List<Task> list = this.getList();
        tableModel.setRowCount(0);

        for (Task item : list) {
            Object[] taskFields = new Object[5];
            taskFields[0] = item.getStatusTask();
            taskFields[1] = item.getTaskName();
            taskFields[2] = formatDate(item.getStartDate());
            taskFields[3] = formatDate(item.getEndDate());
            taskFields[4] = formatDate(item.getConfirmationDate());
            tableModel.addRow(taskFields);
        }
        logger.warn("add rows in taskTable.Count=" + table.getRowCount());

    }

    private String formatDate(Timestamp date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        logger.warn("input date:" + date);
        logger.warn("with format:");

        if (date != null) {
            logger.warn("output date:" + dateFormat.format(date));
            return dateFormat.format(date);
        }
        else {
            logger.warn("output date: ..." );
            return "";
        }

    }
}
