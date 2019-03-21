package cn.liuyan.travel.dao.impl;

import cn.liuyan.travel.dao.UserDao;
import cn.liuyan.travel.domain.User;
import cn.liuyan.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名判断用户是否存在
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        User user = null;
        try {
            user = template.queryForObject("select * from tab_user where username = ?", new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
        }
        return user;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void insert(User user) {
        int update = template.update("insert into tab_user VALUES (NULL,?,?,?,?,?,?,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    //激活码查询用户
    @Override
    public User findCode(String code) {
        User user = null;
        try {
            user = template.queryForObject("select * from tab_user where code = ?", new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    //修改激活状态
    @Override
    public void updateStauts(User user) {
        int update = template.update("UPDATE tab_user set status = 'Y' WHERE uid =?",user.getUid());
    }

    @Override
    public User findUserByNameAndPassword(String username, String password) {
        User user = null;
        try {
            user = template.queryForObject("select * from tab_user where username = ? and password = ?", new BeanPropertyRowMapper<User>(User.class),
                    username, password);
        } catch (DataAccessException e) {

        }
        return user;
    }
}
