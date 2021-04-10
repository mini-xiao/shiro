package com.xiao.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ShiroFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("----------filter Allowed----------");
        log.info("mappedValue："+ mappedValue);
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws java.lang.Exception {
        log.info("----------filter Denied----------");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.getWriter().write("filter Denied");
        return false;
    }

}
