package com.xiao.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class controller {

    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println("name:"+ username);
        System.out.println("pwd:"+ password);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return ae.getMessage();
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @RequestMapping("/current")
    public String current() {
        String code= (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println(code);
        return code;
    }

    @RequestMapping("/test01")
    @RequiresPermissions( "test01")
    public String test01() {
        boolean permitted = SecurityUtils.getSubject().isPermitted("test01");
        System.out.println(permitted);
        return "test01";
    }

    @RequestMapping("/authority")
    @RequiresPermissions( "authority")
    public String authority() {
        boolean permitted = SecurityUtils.getSubject().isPermitted("authority");
        System.out.println(permitted);
        return "authority";
    }
//    @RequestMapping("/logout")
//    public void logout() {
//        System.out.println("logout");
//        SecurityUtils.getSubject().logout();
//    }
}
