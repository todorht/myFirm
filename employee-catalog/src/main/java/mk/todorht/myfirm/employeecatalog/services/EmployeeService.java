package mk.todorht.myfirm.employeecatalog.services;

import mk.todorht.myfirm.employeecatalog.domain.models.Employee;
import mk.todorht.myfirm.employeecatalog.services.form.EmployeeForm;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.services.GenericService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends GenericService<Employee, Integer> {
    List<EmployeeInfo> findAllEmployees();
    Optional<EmployeeInfo> findEmployeeById(int id);
    Employee createEmployee(EmployeeForm employeeForm);
}
