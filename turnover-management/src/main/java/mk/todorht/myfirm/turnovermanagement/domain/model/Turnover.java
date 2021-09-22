package mk.todorht.myfirm.turnovermanagement.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.financial.Currency;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Turnover {

    @EmbeddedId
    private TurnoverId turnoverId;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="bonus_amount")),
            @AttributeOverride(name="currency", column = @Column(name="bonus_currency"))
    })
    private Money total;
    private boolean achievedLimit;

    public Turnover(TurnoverId turnoverId) {
        this.turnoverId = turnoverId;
        this.total = new Money(Currency.MKD,0);
        this.achievedLimit = false;
    }

    public void addAmount(Money money){
        this.total = money;
    }

    public void markAsAchieved(){
        this.achievedLimit = true;
    }
}
