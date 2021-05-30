package com.xiao.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {
    private static final String name = "admin";
    private static final String password = "123456";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------用户权限授权-------------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        // 连接数据库查询对应的账号权限，传入SimpleAuthenticationInfo中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("authority");
        info.setStringPermissions(stringSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        // 连接数据库查询对应账号密码，传入SimpleAuthenticationInfo中
        // 此处需校验对应账号是否存在
        if (!userName.equals(name)) throw new AccountException("账号不存在");
        return new SimpleAuthenticationInfo(name, password, getName());
    }
}
