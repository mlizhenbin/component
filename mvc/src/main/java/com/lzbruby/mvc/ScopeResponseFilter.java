package com.lzbruby.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：处理跨域Filter
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：lzbruby.org
 * Date: 16/6/12 Time: 17:47
 */
public class ScopeResponseFilter implements Filter {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeResponseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type, Authorization, openToken");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);

        LOGGER.debug("ScopeResponseFilter Access-Control-Allow-Origin={}", origin);

        chain.doFilter(request, response);

        return;
    }

    @Override
    public void destroy() {

    }
}
