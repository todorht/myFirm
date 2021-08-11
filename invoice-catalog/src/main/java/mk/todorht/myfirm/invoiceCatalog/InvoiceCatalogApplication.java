package mk.todorht.myfirm.invoiceCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InvoiceCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceCatalogApplication.class, args);
    }

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
