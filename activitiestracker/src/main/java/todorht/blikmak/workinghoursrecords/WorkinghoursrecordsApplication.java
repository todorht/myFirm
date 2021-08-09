package todorht.blikmak.workinghoursrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import todorht.projects.myfirm.config.filters.JwtAuthorizationFilter;


@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class WorkinghoursrecordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkinghoursrecordsApplication.class, args);
    }

}
