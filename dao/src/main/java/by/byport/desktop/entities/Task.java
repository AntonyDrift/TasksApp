package by.byport.desktop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "employees")
@Entity
@Component
public class Task implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String taskName;
    @Column()
    private Timestamp startDate;
    @Column
    private Timestamp endDate;
    @Column
    private Timestamp confirmationDate;
    @Column
    private Boolean statusTask;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_employee",
    joinColumns = @JoinColumn(name = "task_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public Task(String taskName, Timestamp endDate) {
        id=null;
        this.taskName = taskName;
        startDate = new Timestamp(System.currentTimeMillis());
        this.endDate = endDate;
        confirmationDate = null;
        employees = new ArrayList<>();
    }
}
