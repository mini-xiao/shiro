package com.xiao.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@ControllerAdvice
public class Exception {

    @ExceptionHandler(value = AuthorizationException.class)
    public String authorizationError(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) throws IOException {
        String requestURI = request.getRequestURI();
        log.info("授权异常请求：[{}]，账号：[{}]", requestURI,SecurityUtils.getSubject().getPrincipal());
        return "false";
    }
}
