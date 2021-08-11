package mk.todorht.myfirm.sharedkernel.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentInfo<ID extends Serializable> {

    @EmbeddedId
    private ID id;

    private String companyName;
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amount")),
            @AttributeOverride(name="currency", column = @Column(name="currency"))
    })
    private Money amount;

    private LocalDate createAt;
    private LocalDate expiresAt;
    private boolean paid;

    public void markAsPaid(){
        paid = true;
    }


}
