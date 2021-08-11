package mk.todorht.myfirm.employeecatalog.services.impl;

import mk.todorht.myfirm.employeecatalog.domain.exceptions.EmployeeWithThisIdAlreadyExist;
import mk.todorht.myfirm.employeecatalog.domain.models.Employee;
import mk.todorht.myfirm.employeecatalog.domain.repository.EmployeeRepository;
import mk.todorht.myfirm.employeecatalog.services.form.EmployeeForm;
import mk.todorht.myfirm.employeecatalog.services.EmployeeService;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Integer> implements EmployeeService  {


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

    private EmployeeInfo mapFrom(Employee employee){
        return new EmployeeInfo(employee.getId(), employee.getName(), employee.getSurname(), employee.getCardNumber());
    }

    @Override
    public List<EmployeeInfo> findAllEmployees() {
        return findAll().stream()
                .map(this::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeInfo> findEmployeeById(int id) {
        return findById(id).map(this::mapFrom);
    }

    @Override
    public Optional<EmployeeInfo> findEmployeeByCardNumber(String cardNumber) {
        var employee = findAll().stream().filter(employee1 -> employee1.getCardNumber().equals(cardNumber)).findFirst();
        return employee.map(this::mapFrom);
    }

    @Override
    public Employee createEmployee(EmployeeForm employeeForm) {
        if(findById(employeeForm.getId()).isPresent()) throw new EmployeeWithThisIdAlreadyExist();
        Employee e = Employee.build(employeeForm.getId(), employeeForm.getName(), employeeForm.getSurname(), employeeForm.getCardNumber(), employeeForm.getEmail());
        save(e);
        return e;
    }
}
