package by.byport.desktop.entities;


import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String fullName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;

    public Employee(String fullName) {
        id= null;
        this.fullName = fullName;
        tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return fullName;
    }
}



