package com.soulrebel.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTimeElapsedFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostTimeElapsedFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        LOGGER.info(" Entering to post filter ");
        Long timeInit = (Long) request.getAttribute("timeInit");
        Long finalInit = System.currentTimeMillis( );
        Long timeElapsed = finalInit - timeInit;
        LOGGER.info(String.format("Time elapsed in seconds %s secs", timeElapsed.doubleValue( ) / 1000.00));
        LOGGER.info(String.format("Time elapsed in mil-seconds %s ms", timeElapsed / 1000.00));
        return null;
    }
}
