package com.taotao.rest.service.impl;

import com.taotao.pojo.TbItemCat;
import com.taotao.service.api.ItemCatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {
    @Override
    public List getItemCatList(Long parentId) {
        return null;
    }

    @Override
    public List<TbItemCat> getItemCatList() {

        return null;
    }

    //测试dubbo服务是否被消费
    @Override
    public String getIndex() {
        return "index";
    }
}
