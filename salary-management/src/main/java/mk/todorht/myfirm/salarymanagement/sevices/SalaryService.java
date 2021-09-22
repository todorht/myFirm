package mk.todorht.myfirm.salarymanagement.sevices;

import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import mk.todorht.myfirm.sharedkernel.financial.Money;
import mk.todorht.myfirm.sharedkernel.services.GenericService;

import java.time.LocalDate;

public interface SalaryService extends GenericService<Salary, SalaryId> {
    Salary create(int employeeId, int month, int year);
    void addSalaryItem(SalaryItemCreated salaryItem);
    Salary getSalary(SalaryId salaryId);
    void removePenalty(SalaryId salaryId);
    void addBonus(SalaryId salaryId, Money money);
}
