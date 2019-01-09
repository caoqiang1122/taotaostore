package com.taotao.portal.controller;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title 购物车管理
 * @Description: 购物车管理
 * @Author caoqiang
 * @Date 2019/1/9 14:29
 * @Version 1.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车商品
     * @param itemId
     * @param itemNum
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, Integer itemNum, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult taotaoResult = cartService.addCartItem(itemId,itemNum,request,response);
        if (taotaoResult.getStatus() == 200){
            return "cart-success";
        }
        return "error/exception";
    }

    /**
     * 展示购物车商品
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/cart")
    public String showCart(Model model, HttpServletRequest request) {
        List<Item> list = cartService.getCatItemList(request);
        model.addAttribute("cartList", list);
        return "cart";
    }

    /**
     * 更新购物车商品数量
     * @param itemId
     * @param itemNum
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/update/num/{itemId}/{itemNum}")
    @ResponseBody
    public TaotaoResult updateItemNum(@PathVariable Long itemId, @PathVariable Integer itemNum,
                                      HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = cartService.updateItemNum(itemId, itemNum, request, response);
        return result;
    }
}
