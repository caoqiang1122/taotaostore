package com.taotao.portal.service.impl;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtil;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.pojo.OrderCart;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title 订单管理
 * @Description: 订单管理
 * @Author caoqiang
 * @Date 2019/1/9 14:45
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;

    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    /**
     * 查询订单信息
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @Override
    public OrderCart getOrderCart(Long userId, HttpServletRequest request, HttpServletResponse response) {
        //根据id查询用户的配送地址列表（未实现）
        //从cookie中取商品列表
        List<Item> list = cartServiceImpl.getItemListFromCart(request, response);
        OrderCart orderCart = new OrderCart();
        orderCart.setItemList(list);
        return orderCart;
    }

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {
        //先把orderInfo转换成json数据
        String json = JsonUtil.objectToJson(orderInfo);
        //调用订单系统的服务
        String string = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
        //把string转换成TaotaoResult对象
        TaotaoResult result = TaotaoResult.format(string);

        return result;
    }
}
