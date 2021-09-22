package mk.todorht.myfirm.salarymanagement.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.financial.Currency;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "salary")
@NoArgsConstructor
@Getter
public class Salary {

    @EmbeddedId
    private SalaryId salaryId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name", column = @Column(name="employee_name")),
            @AttributeOverride(name="surname", column = @Column(name="employee_surname"))
    })
    private EmployeeInfo employeeInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SalaryItem> salaryItemList;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="bonus_amount")),
            @AttributeOverride(name="currency", column = @Column(name="bonus_currency"))
    })
    private Money bonus;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="penalty_amount")),
            @AttributeOverride(name="currency", column = @Column(name="penalty_currency"))
    })
    private Money penalty;

    public void addSalaryItem(@NotNull SalaryItem salaryItem){
        this.salaryItemList.add(salaryItem);
    }

    public void addBonus(Money money){
        this.bonus = money;
    }


    public void removeDefaultPenalty(){
        this.penalty = this.penalty.subtract(new Money(Currency.MKD,5000));
    }

    public Money total(){
        var amount = this.salaryItemList.stream().map(SalaryItem::getPercentForPayment)
                .reduce(new Money(Currency.MKD,0), Money::add);
        amount.add(this.bonus);
        amount.subtract(this.penalty);
        return amount;
    }

    public Salary(SalaryId salaryId, EmployeeInfo employeeInfo) {
        this.salaryId = salaryId;
        this.employeeInfo = employeeInfo;
        this.bonus= new Money(Currency.MKD,0);
        this.penalty= new Money(Currency.MKD,5000);
        this.salaryItemList= new ArrayList<>();
    }
}
