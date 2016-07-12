package com.mouse.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/30
 *
 * 各个接口线程隔离隔离器
 * 对于高危接口进行隔离,确保不影响其它接口的可用性
 *
 */
public class ThreadInsulateFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ThreadInsulateFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Init thread insulate filter.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        log.debug("Destroy thread insulate filter.");
    }
}
