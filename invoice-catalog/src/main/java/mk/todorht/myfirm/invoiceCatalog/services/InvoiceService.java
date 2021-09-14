package mk.todorht.myfirm.invoiceCatalog.services;

import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;
import mk.todorht.myfirm.invoiceCatalog.services.form.InvoiceForm;
import mk.todorht.myfirm.invoiceCatalog.services.form.PaymentForm;
import mk.todorht.myfirm.sharedkernel.services.GenericService;

import java.time.LocalDate;
import java.util.List;


public interface InvoiceService extends GenericService<Invoice, InvoiceId> {
    void markAsPaid(PaymentForm paymentForm);
    Invoice createInvoice(InvoiceForm invoiceForm);
    List<Invoice> findAllByEmployeeId(Integer employeeId);
}
