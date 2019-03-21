package com.liuyan.service;

import com.liuyan.domain.Items;

import java.util.List;

public interface ItemsService {
    /*查询所有*/
    List<Items> findAll();

    /*保存用户*/
    void saveItem(Items items);
}
