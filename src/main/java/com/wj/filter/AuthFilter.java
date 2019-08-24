package com.wj.filter;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.ResourceURL;
import com.wj.service.impl.RedisService;
import com.wj.service.impl.ResourceUrlServiceImpl;
import com.wj.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jun.wang
 * @title: AuthFilter
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/15 9:52
 */

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisService redisService;

    private static final String ACCESS_TOKEN = "access_token";

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static final String check_token_url = "http://localhost:9920/uaa/checkToken";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        authCheck(exchange);
        return chain.filter(exchange);
    }

    private void authCheck(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        List<ResourceURL> openResourceUrlList = redisService.get(ResourceUrlServiceImpl.OPEN_RESOURCE_URL_KEY);
        List<ResourceURL> privateResourceUrlList = redisService.get(ResourceUrlServiceImpl.PRIVATE_RESOURCE_URL_KEY);
        List<ResourceURL> allResourceList = new ArrayList<>();
        if (openResourceUrlList != null) {
            allResourceList.addAll(openResourceUrlList);
        }
        if (privateResourceUrlList != null) {
            allResourceList.addAll(privateResourceUrlList);
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setCaseSensitive(false);
        boolean exists = allResourceList.stream().anyMatch(resourceURL ->antPathMatcher.match(resourceURL.getUrl(), url) && checkMethod(exchange, resourceURL.getMethod()));
        if (!exists) {
            throw new  RuntimeException("path not found");
        }
        if (privateResourceUrlList == null) {
            return;
        }
        boolean privatePass = privateResourceUrlList.stream().anyMatch(resourceURL ->antPathMatcher.match(resourceURL.getUrl(), url));
        if (privatePass) {
            String token = getAccessToken(exchange);
            if (StringUtils.isEmpty(token)) {
                logger.error("token is empty");
                throw new  RuntimeException("token is empty");
            }
            String checkTokenUrl = check_token_url + "?" + ACCESS_TOKEN + "=" + token;
            String result = HttpClientUtil.httpGetRequest(checkTokenUrl);
            if (StringUtils.isEmpty(result)) {
                logger.error("check token error");
                throw new  RuntimeException("check token error");
            }
            JSONObject json = JSONObject.parseObject(result);
            if (!json.containsKey("code")) {
                logger.error("invalid_token");
                throw new  RuntimeException("invalid_token");
            }
        }
    }

    private String getAccessToken(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        List<String> tokens = params.get(ACCESS_TOKEN);
        String token = "";
        if (tokens != null && tokens.size() > 0) {
            token = tokens.get(0);
        }

        HttpHeaders httpHeaders = request.getHeaders();
        if (!httpHeaders.isEmpty()) {
            tokens = httpHeaders.get(ACCESS_TOKEN);
            if (tokens != null && tokens.size() > 0) {
                token = tokens.get(0);
            }
        }
        return token;
    }

    private boolean checkMethod(ServerWebExchange exchange, String method) {
        //GatewayAutoConfiguration DefaultServerCodecConfigurer
        ServerHttpRequest request = exchange.getRequest();
        return request.getMethod().name().equalsIgnoreCase(method);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
