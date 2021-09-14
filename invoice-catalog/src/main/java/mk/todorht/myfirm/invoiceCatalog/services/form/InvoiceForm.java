package mk.todorht.myfirm.invoiceCatalog.services.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.todorht.myfirm.sharedkernel.financial.Currency;
import mk.todorht.myfirm.sharedkernel.financial.Money;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InvoiceForm {

    @NotNull
    private long invoice_num;
    @NotNull
    private int employeeId;
    @NotNull
    @Min(1500)
    private double amount;
    @NotNull
    private LocalDate createAt;
    @NotNull
    private String companyName;

}
