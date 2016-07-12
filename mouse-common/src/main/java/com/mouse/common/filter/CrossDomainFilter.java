package com.mouse.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/30
 *
 * 跨域过滤器
 * 旨在使用CORS解决服务端跨域问题
 *
 * Refer to : http://www.ruanyifeng.com/blog/2016/04/cors.html
 *
 * JSONP 原理 : http://www.cnblogs.com/chopper/archive/2012/03/24/2403945.html
 *
 * 优缺点
 * JSONP只支持GET请求，CORS支持所有类型的HTTP请求。JSONP的优势在于支持老式浏览器，以及可以向不支持CORS的网站请求数据。
 */
public class CrossDomainFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(CrossDomainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Init cross domain filter.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "un, pw, sk, sc, pn, lt, ct, st, Accept, Content-Type\n");
        ((HttpServletResponse)response).addHeader("Access-Control-Max-Age", "1728000");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest)request).getMethod())) {
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.debug("Destroy cross domain filter.");
    }
}
