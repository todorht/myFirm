package mk.todorht.myfirm.salarymanagement.sevices;

import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.salarymanagement.sevices.form.SalaryForm;
import mk.todorht.myfirm.salarymanagement.sevices.form.SalaryItemForm;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import mk.todorht.myfirm.sharedkernel.financial.Money;
import mk.todorht.myfirm.sharedkernel.services.GenericService;

import java.time.LocalDate;

public interface SalaryService extends GenericService<Salary, SalaryId> {
    Salary create(int employeeId, int month, int year);
    void confirmSalary(SalaryId salaryId, SalaryForm salaryForm);
    void addSalaryItem(String json);
    //
}
