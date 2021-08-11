package mk.todorht.myfirm.invoiceCatalog.xport;

import lombok.AllArgsConstructor;
import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;
import mk.todorht.myfirm.invoiceCatalog.services.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice-catalog")
@AllArgsConstructor
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getAllInvoices(){
        return this.invoiceService.findAll();
    }

    @GetMapping("/")
    public ResponseEntity<Invoice> getInvoice(@RequestBody InvoiceId invoiceId){
        var invoice = this.invoiceService.findById(invoiceId);
        return invoice.map(i -> ResponseEntity.ok().body(i))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/")
    public void markAsPaid(@RequestBody InvoiceId invoiceId){
        this.invoiceService.markAsPaid(invoiceId);
    }

    @GetMapping("/{employeeId}")
    public List<Invoice> getAllInvoiceByEmployee(@PathVariable Integer employeeId){
        return this.invoiceService.findAllByEmployeeId(employeeId);
    }


}
