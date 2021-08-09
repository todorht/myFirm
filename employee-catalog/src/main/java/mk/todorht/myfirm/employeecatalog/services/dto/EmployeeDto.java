package mk.todorht.myfirm.employeecatalog.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class EmployeeDto{

    private int id;
    private String name;
    private String surname;
    private String cardNumber;

}
