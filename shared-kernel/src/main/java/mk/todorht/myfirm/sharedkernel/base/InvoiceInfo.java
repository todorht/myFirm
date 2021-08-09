package mk.todorht.myfirm.sharedkernel.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class InvoiceInfo {

    private String companyName;
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amount")),
            @AttributeOverride(name="currency", column = @Column(name="currency"))
    })
    private Money amount;

    private Date createAt;
    private Date expiresAt;
    private boolean paid;
}
