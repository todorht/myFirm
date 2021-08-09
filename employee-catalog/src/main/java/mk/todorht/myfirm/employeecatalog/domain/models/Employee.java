package mk.todorht.myfirm.employeecatalog.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Employee implements Serializable {

    @Id
    private int id;
    private String name;
    private String surname;
    private String cardNumber;
    private String email;

}
