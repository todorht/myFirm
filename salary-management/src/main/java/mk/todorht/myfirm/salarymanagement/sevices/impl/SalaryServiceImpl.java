package mk.todorht.myfirm.salarymanagement.sevices.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryItem;
import mk.todorht.myfirm.salarymanagement.domain.repository.SalaryRepository;
import mk.todorht.myfirm.salarymanagement.sevices.SalaryService;
import mk.todorht.myfirm.salarymanagement.sevices.form.SalaryForm;
import mk.todorht.myfirm.salarymanagement.sevices.form.SalaryItemForm;
import mk.todorht.myfirm.salarymanagement.web.client.EmployeeClient;
import mk.todorht.myfirm.sharedkernel.events.salary.SalaryItemCreated;
import mk.todorht.myfirm.sharedkernel.financial.Money;
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
    public void confirmSalary(SalaryId salaryId, SalaryForm salaryForm) {

    }

    @Override
    public void addSalaryItem(String jsonSalaryItem) {
        ObjectMapper objectMapper = new ObjectMapper();
        SalaryItemCreated salaryItem = null;
        try {
             salaryItem = objectMapper.readValue(jsonSalaryItem, SalaryItemCreated.class);
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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
}
