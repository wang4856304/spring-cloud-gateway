package com.wj;

import com.zaxxer.hikari.pool.ProxyStatement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.actuate.GatewayControllerEndpoint;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.cloud.gateway.route.CompositeRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jun.wang
 * @title: CloudGatewayApp
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/7 16:20
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, LiquibaseAutoConfiguration.class})
@EnableDiscoveryClient
public class CloudGatewayApp {

    public static void main(String args[]) {
        SpringApplication.run(CloudGatewayApp.class, args);
        //RouteDefinitionRouteLocator
        //RequestRateLimiterGatewayFilterFactory
        //RoutePredicateHandlerMapping
        //GatewayFilter
        //Ordered
        //GlobalFilter
        List<String> list = new LinkedList<>();
        list.add("");
        //NettyWriteResponseFilter
        System.setProperty("hibernate.dialect.storage_engine", "innodb");
    }

    /*@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("", r -> r.path("/test/**")
                        .uri("http://localhost:9820/test/testRes")
                        .uri("http://localhost:9820/test/testJpa")
                )
                .build();
    }*/
}
