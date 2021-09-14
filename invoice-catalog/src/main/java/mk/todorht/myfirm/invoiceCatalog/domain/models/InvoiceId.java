package mk.todorht.myfirm.invoiceCatalog.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceId invoiceId = (InvoiceId) o;
        return invoice_num == invoiceId.invoice_num && year == invoiceId.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_num, year);
    }
}
