package mk.todorht.myfirm.invoiceCatalog.domain.repository;

import mk.todorht.myfirm.invoiceCatalog.domain.models.Invoice;
import mk.todorht.myfirm.invoiceCatalog.domain.models.InvoiceId;
import mk.todorht.myfirm.sharedkernel.repository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends GenericRepository<Invoice, InvoiceId> {
}
