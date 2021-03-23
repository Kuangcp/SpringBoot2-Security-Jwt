package com.github.kuangcp.graduate.controller;

import com.github.kuangcp.graduate.util.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午12:29
 */

@RestController
class TestJWTController {

    // 路由映射到/users 并且限制请求类型仅仅为json
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String usersList() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("tom");
            add("jerry");
        }};

        return JSONResult.fillResultString(0, "", users);
    }

    @RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
    public String user() {
        return JSONResult.fillResultString(0, "user", null);
    }

    @RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
    public String admin() {
        return JSONResult.fillResultString(0, "admin", null);
    }
}