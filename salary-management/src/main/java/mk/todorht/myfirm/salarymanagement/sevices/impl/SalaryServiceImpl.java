package mk.todorht.myfirm.salarymanagement.sevices.impl;

import mk.todorht.myfirm.salarymanagement.domain.exceptions.SalaryItemNullException;
import mk.todorht.myfirm.salarymanagement.domain.exceptions.SalaryNotFoundedException;
import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryItem;
import mk.todorht.myfirm.salarymanagement.domain.repository.SalaryRepository;
import mk.todorht.myfirm.salarymanagement.sevices.SalaryService;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import mk.todorht.myfirm.sharedkernel.financial.Money;
import mk.todorht.myfirm.sharedkernel.services.EmployeeClient;
import mk.todorht.myfirm.sharedkernel.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class SalaryServiceImpl extends GenericServiceImpl<Salary, SalaryId> implements SalaryService {

    private final EmployeeClient employeeClient;

    public SalaryServiceImpl(SalaryRepository salaryRepository, EmployeeClient employeeClient) {
        super(salaryRepository);

        this.employeeClient = employeeClient;
    }

    @Override
    public Salary create(int employeeId, int month, int year) {
        SalaryId salaryId = new SalaryId(employeeId, month, year);
        if(findById(salaryId).isEmpty()){
            var employeeInfo = employeeClient.findEmployeeById(employeeId);
            return save(new Salary(salaryId,employeeInfo.getBody()));
        }
        return findById(salaryId).get();
    }

    @Override
    public void addSalaryItem(SalaryItemCreated salaryItem) {
        if(salaryItem==null) {
            throw new SalaryItemNullException();
        }
        SalaryId salaryId = new SalaryId(salaryItem.getEmployeeId(),LocalDate.parse(salaryItem.getPaymentDate()).getMonthValue(),LocalDate.parse(salaryItem.getPaymentDate()).getYear());
        Salary salary = create(salaryId.getEmployee_id(), salaryId.getMonth(), salaryId.getYear());
        salary.addSalaryItem(new SalaryItem(salaryItem.getInvoiceNum(), salaryId.getYear(), salaryItem.getCustomerName(), salaryItem.getAmount()));

        try{
            save(salary);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Salary getSalary(SalaryId salaryId) {
        var salary = this.findById(salaryId);
        if(salary.isPresent()) {
            return salary.get();
        }else throw new SalaryNotFoundedException();
    }

    @Override
    public void removePenalty(SalaryId salaryId) {
        Salary salary = getSalary(salaryId);
        salary.removeDefaultPenalty();
        save(salary);
    }

    @Override
    public void addBonus(SalaryId salaryId, Money money) {
        Salary salary = getSalary(salaryId);
        salary.addBonus(money);
        save(salary);
    }

}
