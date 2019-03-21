package cn.liuyan.travel.dao.impl;

import cn.liuyan.travel.dao.CategoryDao;
import cn.liuyan.travel.domain.Category;
import cn.liuyan.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao{

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //查询所有
    @Override
    public List<Category> findAll() {
        List<Category> list = null;
        try {
            list = template.query("select * from tab_category ORDER BY cid", new BeanPropertyRowMapper<Category>(Category.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
