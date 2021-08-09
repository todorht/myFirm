package mk.todorht.myfirm.invoiceCatalog.domain.models;

import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.base.InvoiceInfo;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inovoices")
@NoArgsConstructor
public class Invoice extends InvoiceInfo {

    @EmbeddedId
    private InvoiceId invoiceId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column = @Column(name = "employye_id")),
            @AttributeOverride(name="name", column = @Column(name="employee_name")),
            @AttributeOverride(name="surname", column = @Column(name="employee_surname"))
    })
    private EmployeeInfo employee;

    public Invoice(InvoiceId invoiceId, String companyName, Money amount, Date createAt, Date expiresAt, boolean paid, EmployeeInfo employee) {
        super(companyName, amount, createAt, expiresAt, paid);
        this.invoiceId = invoiceId;
        this.employee = employee;
    }

}
