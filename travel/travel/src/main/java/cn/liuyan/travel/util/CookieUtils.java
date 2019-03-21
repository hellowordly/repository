package cn.liuyan.travel.util;

import javax.servlet.http.Cookie;

public class CookieUtils {

    public static Cookie find(Cookie[] cookies, String name) {
        if(cookies == null) {
            return null;
        } else {
            //找到名称为name的cookie
            for (Cookie cookie : cookies) {
                if(name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}