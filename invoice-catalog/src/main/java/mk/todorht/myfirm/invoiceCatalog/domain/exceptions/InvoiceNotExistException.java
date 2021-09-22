package mk.todorht.myfirm.invoiceCatalog.domain.exceptions;

import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;

public class InvoiceNotExistException extends RuntimeException{
    public InvoiceNotExistException(InvoiceId invoiceNum){
        System.out.println(invoiceNum.getInvoice_num() + " " + invoiceNum.getYear());
    }
}
