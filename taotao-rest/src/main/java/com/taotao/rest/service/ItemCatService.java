package com.taotao.rest.service;

import com.taotao.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {

    public List getItemCatList(Long parentId);

    List<TbItemCat> getItemCatList();
}
