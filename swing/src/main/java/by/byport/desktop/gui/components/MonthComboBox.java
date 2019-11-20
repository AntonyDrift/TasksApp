package by.byport.desktop.gui.components;

import by.byport.desktop.gui.SimpleMonth;
import by.byport.desktop.services.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

@Component
public class MonthComboBox {

    private static Logger logger = Logger.getLogger(MonthComboBox.class);

    @Autowired
    private TaskService taskService;
    private JComboBox comboBox;

    public MonthComboBox() {
        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(150, 23));
        logger.warn("init monthComboBox:"+comboBox);
    }

    public JComboBox getComboBox() {
        logger.warn("get comboBox:" + comboBox);
        return this.comboBox;
    }

    public void addMonth() {

        List<SimpleMonth> list = new ArrayList<>();

        list.add(new SimpleMonth(1, "Январь"));
        list.add(new SimpleMonth(2, "Февраль"));
        list.add(new SimpleMonth(3, "Март"));
        list.add(new SimpleMonth(4, "Апрель"));
        list.add(new SimpleMonth(5, "Май"));
        list.add(new SimpleMonth(6, "Июнь"));
        list.add(new SimpleMonth(7, "Июль"));
        list.add(new SimpleMonth(8, "Август"));
        list.add(new SimpleMonth(9, "Сентябрь"));
        list.add(new SimpleMonth(10, "Октябрь"));
        list.add(new SimpleMonth(11, "Ноябрь"));
        list.add(new SimpleMonth(12, "Декабрь"));

        for (SimpleMonth item : list) {
            comboBox.addItem(item);
        }
        logger.warn("add month in combobox:" + list);
    }

}
