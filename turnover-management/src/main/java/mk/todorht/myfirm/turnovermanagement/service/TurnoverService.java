package mk.todorht.myfirm.turnovermanagement.service;

import mk.todorht.myfirm.sharedkernel.events.turnover.InvoiceCreated;
import mk.todorht.myfirm.sharedkernel.services.GenericService;
import mk.todorht.myfirm.turnovermanagement.domain.model.Turnover;
import mk.todorht.myfirm.turnovermanagement.domain.model.TurnoverId;

public interface TurnoverService extends GenericService<Turnover, TurnoverId> {
    Turnover create(int employeeId, int month, int year);
    void increaseTurnoverAmount(InvoiceCreated invoiceCreated);
}
