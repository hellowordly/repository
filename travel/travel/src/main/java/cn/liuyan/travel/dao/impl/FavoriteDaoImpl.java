package cn.liuyan.travel.dao.impl;

import cn.liuyan.travel.dao.FavoriteDao;
import cn.liuyan.travel.domain.Favorite;
import cn.liuyan.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao
{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid,int uid) {
        Favorite favorites = null;
        try {
            favorites = template.queryForObject("select * from tab_favorite where rid =? and uid = ?", new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            System.out.println("...");
        }
        return favorites;
    }

    @Override
    public int findCountByRid(int rid) {
        Integer count = template.queryForObject("select count(*) from tab_favorite where rid =?", Integer.class, rid);
        return count;
    }

    @Override
    public void add(int rid, int uid) {
        template.update("INSERT INTO tab_favorite VALUES (?,?,?)",rid,new Date(),uid);
    }
}
