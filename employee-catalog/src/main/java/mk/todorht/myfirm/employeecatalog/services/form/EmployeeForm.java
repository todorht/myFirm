package mk.todorht.myfirm.employeecatalog.services.form;

import lombok.Data;

@Data
public class EmployeeForm {
    private int id;
    private String name;
    private String surname;
    private String cardNumber;
    private String email;
}
