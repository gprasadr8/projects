package com.dg.ms.learning.apigateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(predicate -> predicate.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
               .route(predicate -> predicate.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
               .route(predicate -> predicate.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
               .route(predicate -> predicate.path("/currency-conversion-new/**")
                       .filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                               "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))

                .build();
    }
}
