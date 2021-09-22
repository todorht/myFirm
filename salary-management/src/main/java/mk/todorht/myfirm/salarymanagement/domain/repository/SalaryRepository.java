package mk.todorht.myfirm.salarymanagement.domain.repository;

import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.sharedkernel.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends GenericRepository<Salary, SalaryId> {
}
