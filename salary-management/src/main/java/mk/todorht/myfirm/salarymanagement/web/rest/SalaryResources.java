package mk.todorht.myfirm.salarymanagement.web.rest;

import lombok.AllArgsConstructor;
import mk.todorht.myfirm.salarymanagement.domain.model.Salary;
import mk.todorht.myfirm.salarymanagement.sevices.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
