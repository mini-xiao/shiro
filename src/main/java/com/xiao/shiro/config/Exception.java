package com.xiao.shiro.config;

import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class Exception {

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public void authorizationError(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) throws IOException {
        String requestURI = request.getRequestURI();
        log.info("授权异常请求：[{}]，账号：[{}]", requestURI, SecurityUtils.getSubject().getPrincipal());
        Map<String, Object> map = new HashMap<>();
        map.put("status", "500");
        map.put("message", "用户没授权");
        writeJson(map, response);
    }

    private void writeJson(Map<String, Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(new JSONObject(map).toJSONString());
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
