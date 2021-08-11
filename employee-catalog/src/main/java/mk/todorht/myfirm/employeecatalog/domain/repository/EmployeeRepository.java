package mk.todorht.myfirm.employeecatalog.domain.repository;

import mk.todorht.myfirm.employeecatalog.domain.models.Employee;
import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import mk.todorht.myfirm.sharedkernel.repository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface EmployeeRepository extends GenericRepository<Employee, Integer> {
}
