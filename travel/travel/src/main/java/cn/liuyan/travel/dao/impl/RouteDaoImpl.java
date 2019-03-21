package cn.liuyan.travel.dao.impl;

import cn.liuyan.travel.dao.RouteDao;
import cn.liuyan.travel.domain.Route;
import cn.liuyan.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao{

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        //定义一个sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        //创建StringBuilder来拼接sql
        StringBuilder sb = new StringBuilder(sql);
        //定义一个集合,来装可变参数问号的值
        List params =new ArrayList();
        //在搜索的时候可能有两个条件(cid,rname),也可能只有其中一个,所以要先判断条件是否有值
        if(cid!=0){
            //拼接sql
            sb.append(" and cid = ?");

            //给?添加值
            params.add(cid);
        }

        if (rname!=null&&rname.length()!=0){
            sb.append(" and rname like ?");

            params.add("%"+rname+"%");
        }
        sql = sb.toString();
            return template.queryForObject(sql, Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //定义一个sql模板
        String sql = "select * from tab_route where 1=1 ";
        //创建StringBuilder来拼接sql
        StringBuilder sb = new StringBuilder(sql);
        //定义一个集合,来装可变参数问号的值
        List params =new ArrayList();
        //在搜索的时候可能有两个条件(cid,rname),也可能只有其中一个,所以要先判断条件是否有值
        if(cid!=0){
            //拼接sql
            sb.append(" and cid = ?");

            //给?添加值
            params.add(cid);
        }

        if (rname!=null&&rname.length()!=0){
            sb.append(" and rname like ?");

            params.add("%"+rname+"%");
        }

        sb.append(" limit ?,?");//分页查询条件
        params.add(start);
        params.add(pageSize);

        sql = sb.toString();
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
        return list;
    }

    @Override
    public Route findById(int rid) {
        Route route = template.queryForObject("select * from tab_route where rid = ?", new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }
}
