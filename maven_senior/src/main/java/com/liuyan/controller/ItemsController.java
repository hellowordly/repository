package com.liuyan.controller;

import com.liuyan.domain.Items;
import com.liuyan.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层:执行成功了...");
        List<Items> items = itemsService.findAll();
        model.addAttribute("items",items);
        return "list";
    }

    @RequestMapping("/save")
    public String save(Items items) {
        System.out.println("表现层:执行成功了...");
        itemsService.saveItem(items);
        return "redirect:/items/findAll";
    }
}
