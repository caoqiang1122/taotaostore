package com.taotao.portal.controller;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.OrderCart;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title 订单管理
 * @Description: 订单管理
 * @Author caoqiang
 * @Date 2019/1/9 14:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 展示订单
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        //取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        OrderCart orderCart = orderService.getOrderCart(user.getId(), request, response);
        //把物流信息和购物车商品列表传递给jsp
        model.addAttribute("shippingList", orderCart.getShippingList());//未实现
        model.addAttribute("cartList", orderCart.getItemList());
        //返回逻辑视图
        return "order-cart";
    }

    /**
     * 创建订单
     * @param orderInfo
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request, Model model) {
        //取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
//        orderInfo.setUserId(user.getId());
//        orderInfo.setBuyerNick(user.getUsername());
        //调用service创建订单
        TaotaoResult result = orderService.createOrder(orderInfo);
        if (result.getStatus() == 200) {
            model.addAttribute("orderId", result.getData());
//            model.addAttribute("payment", orderInfo.getPayment());
//            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            return "success";
        }
        model.addAttribute("message", result.getMsg());
        return "error/exception";
    }
}
