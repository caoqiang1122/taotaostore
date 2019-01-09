package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.portal.pojo.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    TaotaoResult addCartItem(long itemId, Integer itemNum, HttpServletRequest request, HttpServletResponse response);

    List<Item> getCatItemList(HttpServletRequest request);

    TaotaoResult updateItemNum(long itemId, int itemNum, HttpServletRequest request, HttpServletResponse response);
}
