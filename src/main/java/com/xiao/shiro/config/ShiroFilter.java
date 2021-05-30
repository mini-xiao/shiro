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
        log.info("----------判断是否已登录----------");
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws java.lang.Exception {
        log.info("----------未登录！！！----------");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.getWriter().write("fail");
        return false;
    }

}
