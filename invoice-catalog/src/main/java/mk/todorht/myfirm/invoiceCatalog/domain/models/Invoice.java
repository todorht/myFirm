package mk.todorht.myfirm.invoiceCatalog.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.base.PaymentInfo;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "inovoices")
@NoArgsConstructor
@Getter
public class Invoice extends PaymentInfo<InvoiceId> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column = @Column(name = "employye_id")),
            @AttributeOverride(name="name", column = @Column(name="employee_name")),
            @AttributeOverride(name="surname", column = @Column(name="employee_surname"))
    })
    private EmployeeInfo employee;

    public Invoice(InvoiceId invoiceId,String companyName,
                   EmployeeInfo employee,Money amount, LocalDate createAt,
                   LocalDate expiresAt,  boolean paid) {
        super(invoiceId, companyName, amount, createAt, expiresAt, paid);
        this.employee = employee;
    }

}
