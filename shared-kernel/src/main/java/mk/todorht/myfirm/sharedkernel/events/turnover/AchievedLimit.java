package mk.todorht.myfirm.sharedkernel.events.turnover;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.events.EventItem;

@Getter
@NoArgsConstructor
public class AchievedLimit extends EventItem {
    private int employeeId;
    private int month;
    private int year;

    public AchievedLimit(int employeeId, int month, int year) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
    }
}
