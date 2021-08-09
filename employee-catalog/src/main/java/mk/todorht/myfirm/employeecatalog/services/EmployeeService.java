package mk.todorht.myfirm.employeecatalog.services;


import mk.todorht.myfirm.employeecatalog.domain.models.Employee;
import mk.todorht.myfirm.employeecatalog.services.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> findAll();
    Optional<EmployeeDto> findById(int id);
    Optional<EmployeeDto> findByCardNumber(String cardNumber);
}
