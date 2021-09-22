package mk.todorht.myfirm.sharedkernel.events.turnover;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.events.EventItem;
import mk.todorht.myfirm.sharedkernel.financial.Money;

@Getter
@NoArgsConstructor
public class InvoiceCreated extends EventItem {

    private Integer employeeId;
    private Money amount;
    private String createAt;

    public InvoiceCreated(Integer employeeId, Money amount, String createAt) {
        this.employeeId = employeeId;
        this.amount = amount;
        this.createAt = createAt;
    }
}
