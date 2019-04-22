package com.jazasoft.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TenantContextWebFilter implements WebFilter {
//    private final Logger logger = LoggerFactory.getLogger(TenantContextWebFilter.class);

    public static final String TENANT_HTTP_HEADER = "X-Tenant";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        logger.debug("Inside TenantContextWebFilter...");
        ServerHttpRequest request = exchange.getRequest();
        if (request.getHeaders().containsKey(TENANT_HTTP_HEADER)) {
            String tenant = request.getHeaders().getFirst(TENANT_HTTP_HEADER);
//            logger.debug("X-Tenant header found: {}", tenant);
            CurrentTenantHolder.set(tenant);
        }
        return chain.filter(exchange).doOnSuccessOrError((Void v, Throwable throwable) -> CurrentTenantHolder.remove());
    }
}
