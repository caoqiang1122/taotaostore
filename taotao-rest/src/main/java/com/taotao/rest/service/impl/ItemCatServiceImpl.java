package com.taotao.rest.service.impl;

import com.taotao.pojo.TbItemCat;
import com.taotao.rest.service.ItemCatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Override
    public List getItemCatList(Long parentId) {
        return null;
    }

    @Override
    public List<TbItemCat> getItemCatList() {
        return null;
    }
}
