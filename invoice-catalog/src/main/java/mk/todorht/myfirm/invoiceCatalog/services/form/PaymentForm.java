package mk.todorht.myfirm.invoiceCatalog.services.form;

import lombok.Data;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PaymentForm {

    @NotNull
    private long invoice_num;
    @NotNull
    private LocalDate date;

    public PaymentForm(@NotNull long invoice_num, @NotNull LocalDate date) {
        this.invoice_num = invoice_num;
        this.date = date;
    }
}
