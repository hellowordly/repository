package cn.liuyan.travel.dao;

import cn.liuyan.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询信息
     * @return
     */
    public User findUserByName(String username);

    //添加用户
    public void insert(User user);

    //激活码()
    User findCode(String code);

    //修改激活码
    void updateStauts(User user);

    //查询密码和用户名
    User findUserByNameAndPassword(String username, String password);
}
