package com.liuyan.dao;

import com.liuyan.domain.Items;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsDao {

    /*查询所有*/
    @Select("select * from items")
    List<Items> findAll();

    /*添加用户*/
    @Insert("insert into items values(null,#{name},#{price},#{pic},#{createtime},#{detail})")
    void saveItems(Items items);
}
