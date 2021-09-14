package mk.todorht.myfirm.invoiceCatalog;

import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;
import mk.todorht.myfirm.invoiceCatalog.services.InvoiceService;
import mk.todorht.myfirm.invoiceCatalog.services.form.InvoiceForm;
import mk.todorht.myfirm.invoiceCatalog.services.form.PaymentForm;
import mk.todorht.myfirm.invoiceCatalog.services.impl.InvoiceServiceImpl;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.base.PaymentInfo;
import mk.todorht.myfirm.sharedkernel.financial.Currency;
import mk.todorht.myfirm.sharedkernel.financial.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class InvoiceCatalogApplicationTests {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateInvoice(){
        InvoiceForm invoiceForm
                = new InvoiceForm(5000,1, 1500,
               LocalDate.of(2021,1,15),"Company");

        var invoice = invoiceService.createInvoice(invoiceForm);

        Assert.isInstanceOf(PaymentInfo.class,invoice);

    }

    @Test
    void testEvent(){
//        InvoiceForm invoiceForm
//                = new InvoiceForm(1237,1, 1500,
//                LocalDate.of(2021,8,15),"Company");
//
//        var invoice = invoiceService.createInvoice(invoiceForm);

        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setInvoice_num(1235);
        paymentForm.setDate(LocalDate.now());

        invoiceService.markAsPaid(paymentForm);

    }

}
