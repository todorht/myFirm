package mk.todorht.myfirm.employeecatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.todorht.myfirm.employeecatalog.domain.exceptions.EmployeeNotFounded;
import mk.todorht.myfirm.employeecatalog.domain.models.Employee;
import mk.todorht.myfirm.employeecatalog.domain.repository.EmployeeRepository;
import mk.todorht.myfirm.employeecatalog.services.EmployeeService;
import mk.todorht.myfirm.employeecatalog.services.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private EmployeeDto mapFrom(Employee employee){
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getSurname(), employee.getCardNumber());
    }

    @Override
    public List<EmployeeDto> findAll() {
        return this.employeeRepository.findAll().stream()
                .map(this::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> findById(int id) {
        var employee = this.employeeRepository.findById(id);
        return employee.map(this::mapFrom);
    }

    @Override
    public Optional<EmployeeDto> findByCardNumber(String cardNumber) {
        var employee = this.employeeRepository.findByCardNumber(cardNumber);
        return employee.map(this::mapFrom);
    }
}
