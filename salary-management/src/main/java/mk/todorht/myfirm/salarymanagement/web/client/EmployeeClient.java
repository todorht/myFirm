package mk.todorht.myfirm.salarymanagement.web.client;

import mk.todorht.myfirm.sharedkernel.base.EmployeeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmployeeClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public EmployeeClient(@Value("${app.employee-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public ResponseEntity<EmployeeInfo> findEmployeeById(Integer id) {
        try {
            return restTemplate.getForEntity(uri().path("/employee-catalog/"+id).build().toUri(), EmployeeInfo.class);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}

