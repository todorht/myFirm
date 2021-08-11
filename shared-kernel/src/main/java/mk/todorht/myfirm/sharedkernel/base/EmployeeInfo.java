package mk.todorht.myfirm.sharedkernel.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeInfo implements Serializable {
    public int id;
    public String name;
    public String surname;
    public String cardNumber;
}
