package cn.liuyan.travel.service;

import cn.liuyan.travel.domain.User;

public interface UserService {
    boolean regist(User user);

    //激活码方法
    boolean active(String code);

    //登录方法
    User loginUser(User user);
}
