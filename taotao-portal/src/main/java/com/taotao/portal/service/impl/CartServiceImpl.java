package com.taotao.portal.service.impl;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtil;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.service.CartService;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title 购物车服务
 * @Description: 购物车服务
 * @Author caoqiang
 * @Date 2019/1/8 16:42
 * @Version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${SERVICE_BASE_URL}")
    private String SERVICE_BASE_URL;

    @Value("${ITEM_BASE_URL}")
    private String ITEM_BASE_URL;

    @Value("${CAT_COOKIE_EXPIRE}")
    private Integer CAT_COOKIE_EXPIRE;

    /***
     * 添加购物车商品
     * @param itemId
     * @param itemNum
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult addCartItem(long itemId, Integer itemNum, HttpServletRequest request, HttpServletResponse response) {
        //接受controller传递过来的商品id
        //从cookie中取购物车商品列表
        List<Item> list = getItemListFromCart(request, response);
        boolean flag = false;

        for (Item item : list) {
            if (item.getId() == itemId) {
                flag = true;
                item.setCartItemNum(item.getCartItemNum() + itemNum);
                break;
            }
        }
        //判断是否存在此商品
        if (!flag){
            // 2、根据商品id查询商品信息
            String json = HttpClientUtil.doGet(SERVICE_BASE_URL + ITEM_BASE_URL + itemId);
            // 把json转换成java对象
            TaotaoResult result = TaotaoResult.formatToPojo(json, Item.class);
            Item item = null;
            if (result != null && result.getStatus() == 200) {
                // 取商品信息
                item = (Item) result.getData();

            }
            // 设置数量
            item.setCartItemNum(itemNum);
            // 3、把商品信息放入list
            list.add(item);
        }
        // 4、把list序列号写入cookie，进行编码。
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtil.objectToJson(list), CAT_COOKIE_EXPIRE,"utf-8");
        return TaotaoResult.ok(true);
    }

    /**
     * 取购物车商品列表
     * @param request
     * @return
     */
    @Override
    public List<Item> getCatItemList(HttpServletRequest request) {
        //从cookie中取商品列表
        List<Item> list = getItemListFromCart(request, null);
        return list;
    }

    /**
     * 更新购物车商品数量
     * @param itemId
     * @param itemNum
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult updateItemNum(long itemId, int itemNum, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中把商品取出来
        List<Item> list = getItemListFromCart(request,null);
        //遍历列表找商品
        for (Item item : list){
            if (item.getId().longValue() == itemId){
                item.setCartItemNum(itemNum);
                break;
            }
        }
        //把购物车商品列表写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtil.objectToJson(list), CAT_COOKIE_EXPIRE,
                "utf-8");

        return TaotaoResult.ok(true);
    }

    /**
     * 取购物车信息
     */
    public List<Item> getItemListFromCart(HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取商品列表
        String string = CookieUtils.getCookieValue(request, "TT_CART", "utf-8");
        try {
            List<Item> list = JsonUtil.jsonToList(string, Item.class);
            if (list == null) {
                return new ArrayList<Item>();
            }
            return list;
        } catch (Exception e) {
            return new ArrayList<Item>();
        }
    }
}

