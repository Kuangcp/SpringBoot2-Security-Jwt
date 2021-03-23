package com.github.kuangcp.graduate.constant;

/**
 * @author kuangcp
 */
public interface RoleType {
    String ADMIN = "ADMIN";
    String CUSTOM = "CUSTOM";
    static String  getRole(){
        return ADMIN +","+ CUSTOM;
    }
}
