package mk.todorht.myfirm.sharedkernel.events.salary;

import lombok.Getter;
import mk.todorht.myfirm.sharedkernel.events.DomainEvent;
import mk.todorht.myfirm.sharedkernel.events.EventItem;
import mk.todorht.myfirm.sharedkernel.events.config.TopicHolder;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import java.time.LocalDate;

@Getter
public class SalaryItemCreated extends EventItem {

    private Long invoiceNum;
    private String customerName;
    private Integer employeeId;
    private Money amount;
    private String paymentDate;

    public SalaryItemCreated() { }

    public SalaryItemCreated(Long invoiceNum, String customerName,
                             Integer employeeId, Money amount, LocalDate paymentDate) {
        this.invoiceNum = invoiceNum;
        this.customerName = customerName;
        this.employeeId = employeeId;
        this.amount = amount;
        this.paymentDate = paymentDate.toString();
    }

    @Override
    public String toString() {
        return "SalaryItemCreated{" +
                "invoiceNum=" + invoiceNum +
                ", customerName='" + customerName + '\'' +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
