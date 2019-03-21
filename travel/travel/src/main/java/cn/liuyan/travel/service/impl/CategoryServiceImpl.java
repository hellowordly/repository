package cn.liuyan.travel.service.impl;

import cn.liuyan.travel.dao.CategoryDao;
import cn.liuyan.travel.dao.impl.CategoryDaoImpl;
import cn.liuyan.travel.domain.Category;
import cn.liuyan.travel.service.CategoryService;
import cn.liuyan.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao dao = new CategoryDaoImpl();

    //查询所有
    @Override
    public List<Category> findAll() {
        //redis缓存
        Jedis jedis = JedisUtil.getJedis();
        //查询redis缓存
        Set<Tuple> categorys = jedis.zrangeWithScores("category",0,-1);
        //判断(redis是否有缓存,有查询缓存,没有查询数据库)
        List<Category> list = null;
        if (categorys == null || categorys.size() == 0) {
            System.out.println("aaa");
            //没有缓存
            list = dao.findAll();
            for (int i = 0; i < list.size(); i++) {
                //存入jedis中
                jedis.zadd("category",list.get(i).getCid(),list.get(i).getCname());
            }
        }else {
            System.out.println("bbb");
            //set转化为list
            list = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                list.add(category);
            }
        }
        return list;
    }
}
