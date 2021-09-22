package mk.todorht.myfirm.employeecatalog.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;

    public static Employee build(int id, String name, String surname, String email){
        Employee e = new Employee();
        e.id = id;
        e.name = name;
        e.surname = surname;
        e.email = email;
        return e;
    }

}
