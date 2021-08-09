package mk.todorht.myfirm.invoiceCatalog.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
public class InvoiceId implements Serializable {
    private long invoice_num;
    private int year;

    public InvoiceId(long invoice_num, int year) {
        this.invoice_num = invoice_num;
        this.year = year;
    }
}
