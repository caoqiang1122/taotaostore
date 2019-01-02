package com.taotao.controller;

import com.taotao.common.bean.EasyUiDataGridResponse;
import com.taotao.vo.ResponeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;

import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;


	/**
	 * 系统的首页面
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	/**
	 * 请求和页面的名称一致，进行统一处理
	 * @param page
	 * 		请求名称或者页面名称
	 * @return
	 */
	@RequestMapping("/rest/page/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}

	/**
	 * 分页查询商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/rest/item")
	@ResponseBody
	public EasyUiDataGridResponse getItemList(int page,int rows){
		EasyUiDataGridResponse easyUiDataGridResponse = itemService.getItemList(page, rows);
		return  easyUiDataGridResponse;
	}

	/**
	 * 新增商品
	 * @param item 商品信息
	 * @param desc 商品描述
	 * @return
	 */
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public ResponeResult createItem(TbItem item, String desc) {
		ResponeResult result = itemService.insertItemAndDesc(item, desc);
		return result;
	}


}
