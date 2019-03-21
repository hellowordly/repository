package cn.liuyan.travel.dao;

import cn.liuyan.travel.domain.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> findAll();
}
