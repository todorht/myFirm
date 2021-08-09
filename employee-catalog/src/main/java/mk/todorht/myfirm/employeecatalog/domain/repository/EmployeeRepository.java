package mk.todorht.myfirm.employeecatalog.domain.repository;

import mk.todorht.myfirm.employeecatalog.domain.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByCardNumber(String cardNumber);
}
