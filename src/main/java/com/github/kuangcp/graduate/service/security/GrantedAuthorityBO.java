package com.github.kuangcp.graduate.service.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:45
 */
public class GrantedAuthorityBO implements GrantedAuthority {

    /**
     * role or authority
     * if role must start with ROLE_ such as ROLE_ADMIN
     */
    private String authority;

    public GrantedAuthorityBO(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public static GrantedAuthorityBO buildRole(String role) {
        return new GrantedAuthorityBO("ROLE_" + role);
    }

    public static GrantedAuthorityBO buildAuth(String auth) {
        return new GrantedAuthorityBO(auth);
    }
}
