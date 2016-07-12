package com.mouse.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/3/1
 *
 * 拦截所有请求并记录状态码
 *
 * 主要记录了Jetty业务线程的数量
 */
public class LogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

    private static final AtomicInteger threadCount = new AtomicInteger(0);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            threadCount.incrementAndGet();
            log.info("URI : {}.", ((HttpServletRequest)servletRequest).getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            log.error("Exception while handling request, uri={} cause={}", request.getRequestURI(), ex.getMessage(), ex);
            throw ex;
        } finally {
            threadCount.decrementAndGet();
        }
    }

    @Override
    public void destroy() {

    }
}
