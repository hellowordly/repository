package com.liuyan.service.impl;

import com.liuyan.dao.ItemsDao;
import com.liuyan.domain.Items;
import com.liuyan.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemsService")
public class ItemsServiceImpl implements ItemsService{

    @Autowired
    private ItemsDao itemsDao;


    @Override
    public List<Items> findAll() {
        List<Items> items = itemsDao.findAll();
        System.out.println(items);
        return items;
    }

    @Override
    public void saveItem(Items items) {
        itemsDao.saveItems(items);
    }
}
