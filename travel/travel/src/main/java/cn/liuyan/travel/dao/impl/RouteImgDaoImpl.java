package cn.liuyan.travel.dao.impl;

import cn.liuyan.travel.dao.RouteImgDao;
import cn.liuyan.travel.domain.Route;
import cn.liuyan.travel.domain.RouteImg;
import cn.liuyan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao{

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findById(int rid) {
        List<RouteImg> routeImgList = template.query("select * from tab_route_img where rid = ?", new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
        return routeImgList;
    }
}
