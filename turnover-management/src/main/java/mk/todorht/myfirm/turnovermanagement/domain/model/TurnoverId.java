package mk.todorht.myfirm.turnovermanagement.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class TurnoverId implements Serializable {
    private int employeeId;
    private int month;
    private int year;

    public TurnoverId(int employeeId, int month, int year) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnoverId that = (TurnoverId) o;
        return employeeId == that.employeeId && month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, month, year);
    }
}
