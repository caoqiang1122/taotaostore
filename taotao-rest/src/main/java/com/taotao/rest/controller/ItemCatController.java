package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtil;
import com.taotao.pojo.TbItemCat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
//    @Autowired
//    private ItemCatService itemCatService;
//
//    @RequestMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        List<TbItemCat> tbItemCatList = itemCatService.getItemCatList();
//        if (StringUtils.isBlank(callback)){
//            //结果转换为字符串
//            String json = JsonUtil.objectToJson(tbItemCatList);
//            return json;
//        }
//        String json = JsonUtil.objectToJson(tbItemCatList);
//        return callback + "(" + json + ");";
//    }
}
