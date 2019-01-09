package com.taotao.portal.pojo;

import java.util.List;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/1/9 14:41
 * @Version 1.0
 */
public class OrderCart {

    //商品列表
    private List<Item> itemList ;

    //用户配送地址列表
    private List<Object> shippingList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Object> getShippingList() {
        return shippingList;
    }

    public void setShippingList(List<Object> shippingList) {
        this.shippingList = shippingList;
    }
}
