package com.wj.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jun.wang
 * @title: LoggingTimeFilter
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/7/5 14:31
 */

@Component
public class LoggingTimeFilter implements GlobalFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger("loggingFilter");

    private static final String START_TIME = "start_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Thread thread = new Thread(()->{
            String info = String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}",
                    exchange.getRequest().getMethod().name(),
                    exchange.getRequest().getURI().getHost(),
                    exchange.getRequest().getURI().getPath(),
                    exchange.getRequest().getQueryParams());
            logger.info(info);
        });
        thread.start();

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then( Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                Long executeTime = (System.currentTimeMillis() - startTime);
                //logger.info(exchange.getRequest().getURI().getRawPath() + "$" + executeTime + "ms");
                logger.info("service$"+exchange.getRequest().getURI().getRawPath() + "$" + executeTime);
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
