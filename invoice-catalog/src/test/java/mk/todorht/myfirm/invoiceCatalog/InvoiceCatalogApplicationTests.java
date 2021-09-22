package mk.todorht.myfirm.invoiceCatalog;

import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.services.form.InvoiceForm;
import mk.todorht.myfirm.invoiceCatalog.services.form.PaymentForm;
import mk.todorht.myfirm.invoiceCatalog.services.impl.InvoiceServiceImpl;
import mk.todorht.myfirm.sharedkernel.base.PaymentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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
                = new InvoiceForm(1500,1, 10000,
                LocalDate.of(2021, 12,15),"Todor Test");

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

//        PaymentForm paymentForm = new PaymentForm();
//        paymentForm.setInvoice_num(2525);
//        paymentForm.setDate(LocalDate.of(2021,2,10));
//
//        invoiceService.markAsPaid(paymentForm);

    }


    @Test
    void testServices(){
        long start = System.nanoTime();
        AtomicInteger month = new AtomicInteger(1);
        IntStream.range(1,1200).forEach(i->{
            InvoiceForm invoiceForm
                    = new InvoiceForm(i,1, 10000,
                    LocalDate.of(2021, month.get(),15),"Kompanija" + i);
            Invoice invoice = invoiceService.createInvoice(invoiceForm);
            invoiceService.markAsPaid(new PaymentForm(invoice.getId().getInvoice_num(),invoice.getCreateAt().plusDays(10)));
            if(month.get()==12) month.set(0);
            month.getAndIncrement();
        });
        long end = System.nanoTime();
        long totalTime = end-start;
        System.out.println(totalTime);
    }


    @Test
    void turnoverWithSalaryService(){
        AtomicInteger month = new AtomicInteger(1);
        IntStream.range(1200,1220).forEach(i->{
            InvoiceForm invoiceForm
                    = new InvoiceForm(i,1, 10000,
                    LocalDate.of(2021, month.get(),15),"Kompanija" + i);
            Invoice invoice = invoiceService.createInvoice(invoiceForm);
            invoiceService.markAsPaid(new PaymentForm(invoice.getId().getInvoice_num(),invoice.getCreateAt().plusDays(10)));
            if(month.get()==12) month.set(0);
            month.getAndIncrement();
        });
    }
}
