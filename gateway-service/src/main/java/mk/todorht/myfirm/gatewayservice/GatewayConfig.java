package mk.todorht.myfirm.gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/employee-catalog/**")
                        .uri("lb://EMPLOYEES-CATALOG"))
                .route(r -> r.path("/invoice-catalog/**")
                        .uri("lb://INVOICE-CATALOG"))
                .route(r -> r.path("/turnover/**")
                        .uri("lb://TURNOVER-MANAGEMENT"))
                .route(r -> r.path("/salary/**")
                        .uri("lb://SALARY-MANAGEMENT"))
                .build();
    }
}
