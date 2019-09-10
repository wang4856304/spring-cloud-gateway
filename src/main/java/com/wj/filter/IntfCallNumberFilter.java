package com.wj.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jun.wang
 * @title: IntfCallNumberFilter
 * @projectName ownerpro
 * @description: 统计接口调用次数
 * @date 2019/8/23 17:01
 */
public class IntfCallNumberFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        String serviceName = "";
        if (path.startsWith("/")) {
            int index = path.substring(1).indexOf("/");
            serviceName = path.substring(1, index);
            path = path.substring(index + 1);
        }
        else {
            int index = path.indexOf("/");
            serviceName = path.substring(0, index);
            path = path.substring(index + 1);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
