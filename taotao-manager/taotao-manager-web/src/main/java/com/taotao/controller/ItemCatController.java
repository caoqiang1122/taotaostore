package com.taotao.controller;

import com.taotao.common.bean.EasyUiTreeNodeResult;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rest/item/cat")
public class ItemCatController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUiTreeNodeResult> getItemCatList(@RequestParam(value = "id",defaultValue = "0")long parentId){
        List<EasyUiTreeNodeResult> easyUiTreeNodeResultList= itemService.getItemCatList(parentId);
        return  easyUiTreeNodeResultList;
    }
}
