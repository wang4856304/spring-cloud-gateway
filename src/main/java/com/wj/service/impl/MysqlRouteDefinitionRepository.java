package com.wj.service.impl;

import com.wj.entity.GatewayRoute;
import com.wj.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jun.wang
 * @title: MysqlRouteDefinitionRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/8 15:02
 */

@Component
public class MysqlRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private GatewayRouteService gatewayRouteService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<GatewayRoute> GatewayRouteList = gatewayRouteService.findAll();
        Map<String, RouteDefinition> routes = new LinkedHashMap<>();
        for (GatewayRoute gatewayRoute: GatewayRouteList) {
            RouteDefinition definition = new RouteDefinition();
            definition.setId(gatewayRoute.getId().toString());
            try {
                definition.setUri(new URI(gatewayRoute.getUri()));
            }
            catch (Exception e) {
                throw new  RuntimeException(e);
            }
            List<PredicateDefinition> predicateDefinitions = gatewayRoute.getPredicateDefinition();
            if (predicateDefinitions != null && predicateDefinitions.size() > 0) {
                definition.setPredicates(predicateDefinitions);
            }
            List<FilterDefinition> filterDefinitions = gatewayRoute.getFilterDefinition();
            if (filterDefinitions != null && filterDefinitions.size() > 0) {
                definition.setFilters(filterDefinitions);
            }
            routes.put(definition.getId(), definition);

        }
        return Flux.fromIterable(routes.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
