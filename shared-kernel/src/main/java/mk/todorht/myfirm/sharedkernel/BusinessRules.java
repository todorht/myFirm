package mk.todorht.myfirm.sharedkernel;

import mk.todorht.myfirm.sharedkernel.financial.Currency;
import mk.todorht.myfirm.sharedkernel.financial.Money;

public class BusinessRules {
    public final static Double AMOUNT_LIMIT = 100000.0;
    public final static Double BONUS_LIMIT = 150000.0;
    public final static Money BONUS = new Money(Currency.MKD,500);
}
