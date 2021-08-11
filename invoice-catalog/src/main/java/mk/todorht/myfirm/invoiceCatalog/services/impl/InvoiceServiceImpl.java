package mk.todorht.myfirm.invoiceCatalog.services.impl;

import mk.todorht.myfirm.invoiceCatalog.domain.exceptions.InvoiceNotExistException;
import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;
import mk.todorht.myfirm.invoiceCatalog.domain.repository.InvoiceRepository;
import mk.todorht.myfirm.invoiceCatalog.services.InvoiceService;
import mk.todorht.myfirm.invoiceCatalog.services.form.InvoiceForm;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, InvoiceId> implements InvoiceService {

    public final RestTemplate restTemplate;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, RestTemplate restTemplate) {
        super(invoiceRepository);

        this.restTemplate = restTemplate;
    }


    @Override
    public void markAsPaid(InvoiceId invoiceId) {
        Invoice invoice = findById(invoiceId).orElseThrow(InvoiceNotExistException::new);
        invoice.markAsPaid();
        save(invoice);
        //to do: event for salary domain
    }

    @Override
    public Invoice createInvoice(InvoiceForm invoiceForm) {
        EmployeeInfo employeeInfo = restTemplate.getForObject("http://localhost:8083/employees/" + invoiceForm.getEmployeeId(),EmployeeInfo.class);
        Invoice invoice = new Invoice(new InvoiceId(invoiceForm.getInvoice_num(),invoiceForm.getCreateAt().getYear())
                , invoiceForm.getCompanyName(),employeeInfo,invoiceForm.getMoney(),
                invoiceForm.getCreateAt(),invoiceForm.getExpiresAt(), false);
        save(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> findAllByEmployeeId(Integer employeeId) {
        return findAll().stream().filter(invoice -> invoice.getEmployee().getId() == employeeId)
                .collect(Collectors.toList());
    }
}
