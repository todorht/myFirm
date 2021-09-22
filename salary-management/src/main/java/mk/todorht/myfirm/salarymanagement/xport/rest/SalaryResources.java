package mk.todorht.myfirm.salarymanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.domain.model.SalaryId;
import mk.todorht.myfirm.salarymanagement.sevices.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
@AllArgsConstructor
public class SalaryResources {

    private final SalaryService salaryService;

    @GetMapping
    public List<Salary> getAllSalaries(){
        return this.salaryService.findAll();
    }

    @GetMapping("/")
    public ResponseEntity<Salary> getSalary(@RequestBody SalaryId salaryId){
        Salary salary = this.salaryService.getSalary(salaryId);
        return ResponseEntity.ok().body(salary);
    }
}
