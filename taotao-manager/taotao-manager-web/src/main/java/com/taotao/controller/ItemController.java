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
@RequestMapping("/rest")
public class ItemController {
	@Autowired
	private ItemService itemService;

	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	private TbItem getItemById(@PathVariable Long itemId){
		TbItem item = itemService.getItemById(itemId);
		return item;
	}

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	@RequestMapping("/{page}")
	public String showIndex(@PathVariable String page) {
		return page;
	}
	
	/**
	 * 请求和页面的名称一致，进行统一处理
	 * @param page
	 * 		请求名称或者页面名称
	 * @return
	 */
	@RequestMapping("/page/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}

	@RequestMapping("/item")
	@ResponseBody
	public EasyUiDataGridResponse getItemList(int page,int rows){
		EasyUiDataGridResponse easyUiDataGridResponse = itemService.getItemList(page, rows);
		return  easyUiDataGridResponse;
	}

	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public ResponeResult createItem(TbItem item, String desc) {
		ResponeResult result = itemService.insertItemAndDesc(item, desc);
		return result;
	}


}
