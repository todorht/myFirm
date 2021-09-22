package mk.todorht.myfirm.sharedkernel.events.turnover;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.events.EventItem;
import mk.todorht.myfirm.sharedkernel.financial.Money;

@Getter
@NoArgsConstructor
public class AchievedBonus extends EventItem {
    private int employeeId;
    private int month;
    private int year;
    private Money bonus;

    public AchievedBonus(int employeeId, int month, int year, Money bonus) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.bonus = bonus;
    }
}
