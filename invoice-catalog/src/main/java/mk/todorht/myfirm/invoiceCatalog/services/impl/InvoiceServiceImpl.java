package mk.todorht.myfirm.invoiceCatalog.services.impl;

import mk.todorht.myfirm.invoiceCatalog.domain.exceptions.InvoiceAlreadyExistException;
import mk.todorht.myfirm.invoiceCatalog.domain.exceptions.InvoiceNotExistException;
import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;
import mk.todorht.myfirm.invoiceCatalog.domain.repository.InvoiceRepository;
import mk.todorht.myfirm.invoiceCatalog.services.InvoiceService;
import mk.todorht.myfirm.invoiceCatalog.services.form.InvoiceForm;
import mk.todorht.myfirm.invoiceCatalog.services.form.PaymentForm;
import mk.todorht.myfirm.invoiceCatalog.xport.client.EmployeeClient;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.events.config.TopicHolder;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import mk.todorht.myfirm.sharedkernel.financial.Currency;
import mk.todorht.myfirm.sharedkernel.financial.Money;
import mk.todorht.myfirm.sharedkernel.services.impl.GenericServiceImpl;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;


@Service
@Transactional
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, InvoiceId> implements InvoiceService {

    public final Validator validator;
    private final KafkaTemplate<String, SalaryItemCreated> kafkaTemplate;
    public final EmployeeClient employeeClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, Validator validator, KafkaTemplate<String, SalaryItemCreated> kafkaTemplate, EmployeeClient employeeClient) {
        super(invoiceRepository);

        this.validator = validator;
        this.kafkaTemplate = kafkaTemplate;
        this.employeeClient = employeeClient;
    }

    @Override
    public void markAsPaid(PaymentForm paymentForm) {
        Invoice invoice = findById(new InvoiceId(paymentForm.getInvoice_num(), paymentForm.getDate().getYear())).orElseThrow(InvoiceNotExistException::new);
        invoice.markAsPaid();
        save(invoice);
        if(ChronoUnit.DAYS.between(invoice.getCreateAt(),paymentForm.getDate())<60){
            kafkaTemplate.send(TopicHolder.TOPIC_SALARY_ITEM_CREATED,new SalaryItemCreated(invoice.getId().getInvoice_num(),invoice.getCompanyName(),invoice.getEmployee().getId()
                    ,invoice.getAmount(), paymentForm.getDate()));
        }

    }

    private Invoice build(InvoiceId invoiceId, EmployeeInfo employeeInfo, InvoiceForm invoiceForm){
        return new Invoice(invoiceId, invoiceForm.getCompanyName(), employeeInfo, new Money(Currency.MKD,invoiceForm.getAmount()),
                invoiceForm.getCreateAt(), invoiceForm.getCreateAt().plusDays(30), false);
    }

    @Override
    public Invoice createInvoice(InvoiceForm invoiceForm) {
        Objects.requireNonNull(invoiceForm, "Invoice must not be null");
        var validations = validator.validate(invoiceForm);
        if (validations.size()>0){
            throw new ConstraintViolationException("The invoice form is not valid", validations);
        }
        var employeeInfo = employeeClient.findEmployeeById(invoiceForm.getEmployeeId());

        var invoiceId = new InvoiceId(invoiceForm.getInvoice_num(),invoiceForm.getCreateAt().getYear());
        if(findById(invoiceId).isEmpty()) {
            return save(build(invoiceId,employeeInfo.getBody(),invoiceForm));
        }else throw new InvoiceAlreadyExistException();
    }

    @Override
    public List<Invoice> findAllByEmployeeId(Integer employeeId) {
        return findAll().stream().filter(invoice -> invoice.getEmployee().getId() == employeeId)
                .collect(Collectors.toList());
    }
}
