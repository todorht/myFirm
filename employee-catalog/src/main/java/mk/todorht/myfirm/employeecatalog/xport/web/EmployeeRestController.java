package mk.todorht.myfirm.employeecatalog.xport.web;

import lombok.AllArgsConstructor;
import mk.todorht.myfirm.employeecatalog.services.EmployeeService;
import mk.todorht.myfirm.employeecatalog.services.form.EmployeeForm;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee-catalog")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeInfo> findAll(){
        return this.employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeInfo> findById(@PathVariable(name = "id") int id){
        var employee = this.employeeService.findEmployeeById(id);
        return employee.map(employeeInfo -> ResponseEntity.ok().body(employeeInfo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addEmployee(@RequestBody EmployeeForm employeeForm){
        this.employeeService.createEmployee(employeeForm);
    }
}
