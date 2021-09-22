package mk.todorht.myfirm.sharedkernel.services;

import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeClient {

    private final RestTemplate restTemplate;

    public EmployeeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<EmployeeInfo> findEmployeeById(Integer id) {
        try {
            return restTemplate.getForEntity("http://EMPLOYEES-CATALOG/employee-catalog/"+id, EmployeeInfo.class);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}

