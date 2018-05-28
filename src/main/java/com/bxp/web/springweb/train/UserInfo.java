package com.bxp.web.springweb.train;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: bbp(xiuping bao) on 2018/5/24.
 * @Date: 2018/5/24 21:28
 */
public class UserInfo {
    private String name;
    private String password;
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
