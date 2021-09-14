package mk.todorht.myfirm.salarymanagement.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;

@Entity
@Table(name = "salary_item")
@Getter
@NoArgsConstructor
public class SalaryItem {

    @EmbeddedId
    private SalaryItemId salaryItemId;

    private String costumerName;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="invoice_amount")),
            @AttributeOverride(name="currency", column = @Column(name="invoice_currency"))
    })
    private Money amount;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amount")),
            @AttributeOverride(name="currency", column = @Column(name="currency"))
    })
    private Money percentForPayment;

    public SalaryItem(long salaryItemId, int year, String costumerName, Money amount) {
        this.salaryItemId = new SalaryItemId(salaryItemId,year);
        this.costumerName = costumerName;
        this.amount = amount;
        this.percentForPayment = (((amount.multiply(100)).subtract(amount.multiply(97))).divide(new Money(amount.getCurrency(),100)));
    }
}
