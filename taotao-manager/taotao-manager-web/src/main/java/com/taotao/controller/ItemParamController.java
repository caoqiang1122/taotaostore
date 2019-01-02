package com.taotao.controller;

import com.taotao.common.bean.EasyUiDataGridResponse;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUiDataGridResponse getItemParamList(int page,int rows){
        EasyUiDataGridResponse easyUiDataGridResponse = itemParamService.getItemParamList(page,rows);
        return easyUiDataGridResponse;
    }
}
