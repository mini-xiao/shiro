package com.xiao.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ShiroRealm extends AuthorizingRealm {
    private static final String USER_NAME = "luoguohui";
    private static final String PASSWORD = "123456";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("----------------------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = "123";
        //根据用户名从数据库获取密码
        String password = "123";
        if (userName == null) {
            System.out.println("用户名不正确");
//            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(password )) {
            System.out.println("密码不正确");
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo("admin", password,getName());
    }
}
