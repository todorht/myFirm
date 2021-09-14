package mk.todorht.myfirm.salarymanagement.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class SalaryItemId implements Serializable {

    private long salaryItem_num;
    private int year;

    public SalaryItemId(long invoice_num, int year) {
        this.salaryItem_num = invoice_num;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryItemId that = (SalaryItemId) o;
        return salaryItem_num == that.salaryItem_num && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryItem_num, year);
    }
}
