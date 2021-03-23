package com.github.kuangcp.graduate.service.security;

import com.github.kuangcp.graduate.constant.AuthType;
import com.github.kuangcp.graduate.constant.RoleType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义身份认证验证组件
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 认证逻辑
        if (name.equals("admin") && password.equals("123456")) {
            // 这里设置权限和角色
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(GrantedAuthorityBO.buildRole(RoleType.ADMIN));
            authorities.add(GrantedAuthorityBO.buildAuth(AuthType.WRITE));
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        }
        if (name.equals("user") && password.equals("123456")) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(GrantedAuthorityBO.buildRole(RoleType.CUSTOM));
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        } else {
            throw new BadCredentialsException("密码错误");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}