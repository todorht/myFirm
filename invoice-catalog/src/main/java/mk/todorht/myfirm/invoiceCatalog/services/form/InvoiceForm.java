package mk.todorht.myfirm.invoiceCatalog.services.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class InvoiceForm {
    private long invoice_num;
    private int employeeId;
    private Money money;
    private LocalDate createAt;
    private LocalDate expiresAt;
    private String companyName;

}
