package cn.liuyan.travel.service;

import cn.liuyan.travel.domain.Category;

import java.util.List;

public interface CategoryService {
    //查询所有
    List<Category> findAll();
}
