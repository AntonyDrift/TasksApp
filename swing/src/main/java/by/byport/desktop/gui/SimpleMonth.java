package by.byport.desktop.gui;

import by.byport.desktop.entities.Task;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.List;

@Getter
public class SimpleMonth {

    private static Logger logger = Logger.getLogger(SimpleMonth.class);

    private Integer number;
    private String name;

    public SimpleMonth(Integer number, String name) {
        this.number = number;
        this.name = name;
        logger.warn("init instance of simpleMonth");
    }

    public Boolean hasLeapYear(List<Task> taskList) {
        logger.warn("checking is leap year");
        for (Task item : taskList) {
            int year = item.getStartDate().getYear() + 1900;
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) return true;
        } return  false;
    }

    public Integer getDays(Integer number, Boolean leapYear) {
        Integer[] thirty = new Integer[]{4, 6, 9, 11};
        Integer[] thirtyOne = new Integer[]{1, 3, 5, 7, 8, 10, 12};

        logger.warn("get max days on date: month number=" + number + ";isLeapYear="+leapYear);

        if (number == 2 && leapYear) return Integer.valueOf(29);

        else if (number == 2 && !leapYear) return Integer.valueOf(28);

        else if (number != 2 && number < 13 && number > 0) {

            for (Integer item : thirty) {
                if (item == number) return Integer.valueOf(30);
            }
            for (Integer item : thirtyOne) {
                if (item == number) return Integer.valueOf(31);
            }
        }
        return Integer.valueOf(0);
    }

    @Override
    public String toString() {
        return name;
    }

}
