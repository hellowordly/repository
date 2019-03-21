package cn.liuyan.travel.service.impl;

import cn.liuyan.travel.dao.UserDao;
import cn.liuyan.travel.dao.impl.UserDaoImpl;
import cn.liuyan.travel.domain.User;
import cn.liuyan.travel.service.UserService;
import cn.liuyan.travel.util.MailUtils;
import cn.liuyan.travel.util.UuidUtil;

public class UserServiceImpl implements UserService{

    private UserDao dao =new UserDaoImpl();

    //判断用户名是否存在
    @Override
    public boolean regist(User user) {
        User name = dao.findUserByName(user.getUsername());
        //判断
        if (name != null){
            return  false;
        }
        //设置邮件激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        //添加操作
        dao.insert(user);
        //编辑邮件内容
        String context = "<a href='http://127.0.0.1/travel/user/activeUser?code="+user.getCode()+"'>点击激活[黑马旅游网]</a>";
        //激活邮件
        MailUtils.sendMail(user.getEmail(),context,"激活邮件");
        return true;
    }

    //激活码
    @Override
    public boolean active(String code) {
        //根据激活码查询用户
        User user = dao.findCode(code);
        if (user != null){
            //改变激活状态
            dao.updateStauts(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    //登录
    public User loginUser(User user) {
        User u = dao.findUserByNameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }
}
