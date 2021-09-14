package mk.todorht.myfirm.salarymanagement.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class SalaryId implements Serializable {

    private Integer employee_id;
    private Integer month;
    private Integer year;

    public SalaryId(Integer employee_id, Integer month, Integer year) {
        this.employee_id = employee_id;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId salaryId = (SalaryId) o;
        return Objects.equals(employee_id, salaryId.employee_id) && Objects.equals(month, salaryId.month) && Objects.equals(year, salaryId.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, month, year);
    }
}
