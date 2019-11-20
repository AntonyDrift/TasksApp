package by.byport.desktop.gui.components;

import by.byport.desktop.entities.Task;
import by.byport.desktop.services.TaskService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;


@Component
public class PlanTable {

    private static Logger logger = Logger.getLogger(PlanTable.class);

    @Autowired
    private TaskService taskService;
    @Getter
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    @Getter
    @Setter
    private int days;

    {
        days = 31;
    }

    public PlanTable() {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(23);
        scrollPane = new JScrollPane(table);
        logger.warn("init planTable:" + table);
    }

    public void getHeader() {
        String[] header = new String[days + 1];
        header[0] = "Задача";
        for (int i = 1; i <= days; i++) {
            header[i] = Integer.toString(i);
        }
        tableModel.setColumnIdentifiers(header);
        table.getColumn(header[0]).setMinWidth(120);
        logger.warn("init header in planTable:" + table.getTableHeader());
    }

    public JScrollPane getPlanTable() {
        logger.warn("get planTable with scrollPane:" + scrollPane);
        return this.scrollPane;
    }

    public void addRow(Integer month, Integer daysInMonth, List<Task> taskList) {

        tableModel.setRowCount(0);

        for (Task item : taskList) {

            int startMonth = (item.getStartDate().getMonth()) + 1;
            int endMonth = (item.getEndDate().getMonth()) + 1;
            int startDay = item.getStartDate().getDate();
            int endDay = item.getEndDate().getDate();

            String[] inputRow = new String[daysInMonth + 1];
            inputRow[0] = item.getTaskName();
            //insert false value
            for (int i = 1; i <= daysInMonth; i++) {
                inputRow[i] = "";
            }
            // insert true value methods
            if (startMonth == month && endMonth == month) {
                for (int i = startDay; i <= endDay; i++) {
                    inputRow[i] = " ";
                }
                inputRow[startDay] = "";

            } else if (startMonth == month && endMonth > month) {
                for (int i = startDay; i <= daysInMonth; i++) {
                    inputRow[i] = " ";
                }
                inputRow[startDay] = "";

            } else if (startMonth < month && endMonth == month) {
                for (int i = 1; i <= endDay; i++) {
                    inputRow[i] = " ";
                }

            } else if (startMonth < month && endMonth > month) {
                for (int i = 1; i <= daysInMonth; i++) {
                    inputRow[i] = " ";
                }
            }
            tableModel.addRow(inputRow);
            drawCells(inputRow);
            logger.warn("add rows in planTable. Count=" + tableModel.getRowCount());
        }

    }

    public void drawCells(String[] inputRow) {
        table.setDefaultRenderer(Object.class, new Renderer());
        logger.warn("draw cells in planTable.InputRow=" + inputRow);

    }

    public class Renderer extends DefaultTableCellRenderer {
        public java.awt.Component getTableCellRendererComponent(JTable table,
                                                                Object value,
                                                                boolean isSelected,
                                                                boolean hasFocus,
                                                                int row,
                                                                int column) {
            java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String cellvalue = value.toString();
            if (cellvalue.equals(" ")) {
                cell.setBackground(Color.LIGHT_GRAY);
            } else {
                cell.setBackground(table.getBackground());
            }

            return cell;
        }
    }
}
