package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.portal.pojo.OrderCart;
import com.taotao.portal.pojo.OrderInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {

    OrderCart getOrderCart(Long userId, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult createOrder(OrderInfo orderInfo);
}
