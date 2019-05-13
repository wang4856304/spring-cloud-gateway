package com.wj.keyresolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jun.wang
 * @title: RemoteAddrKeyResolver
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/10 11:01
 */

@Component("remoteAddrKeyResolver")
public class RemoteAddrKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
