package mk.todorht.myfirm.employeecatalog.xport.web;

import lombok.AllArgsConstructor;
import mk.todorht.myfirm.employeecatalog.services.EmployeeService;
import mk.todorht.myfirm.employeecatalog.services.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findAll(){
        return this.employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable(name = "id") int id){
        var employee = this.employeeService.findById(id);
        return employee.map(employeeDto -> ResponseEntity.ok().body(employeeDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<EmployeeDto> findByCardNumber(@PathVariable(name = "cardNumber") String cardNumber){
        var employee = this.employeeService.findByCardNumber(cardNumber);
        return employee.map(employeeDto -> ResponseEntity.ok().body(employeeDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
