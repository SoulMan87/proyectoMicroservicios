package com.soulrebel.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTimeElapsedFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreTimeElapsedFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext( );
        HttpServletRequest request = context.getRequest( );
        LOGGER.info(String.format("%s request routed a %s", request.getMethod( ), request.getRequestURL( ).toString( )));
        Long timeInit = System.currentTimeMillis( );
        request.setAttribute("timeInit", timeInit);

        return null;
    }
}
