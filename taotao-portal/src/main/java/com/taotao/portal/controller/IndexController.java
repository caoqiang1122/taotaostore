package com.taotao.portal.controller;

import com.taotao.service.api.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/index")
    public String showIndex(){
        System.out.println(itemCatService.getIndex());
        return itemCatService.getIndex();
    }
}
