package com.taotao.service;

import com.taotao.common.bean.EasyUiDataGridResponse;
import com.taotao.common.bean.EasyUiTreeNodeResult;
import com.taotao.pojo.TbItem;
import com.taotao.vo.ResponeResult;

import java.util.List;

public interface ItemService {

	/**
	 * 根据商品id查询商品信息
	 * @param itemId 
	 * 		商品id
	 * @return TbItem
	 * 		商品信息
	 */
	TbItem getItemById(Long itemId);

	/**
	 * 分页查询商品列表
	 * */
	EasyUiDataGridResponse getItemList(int page, int rows);

	/**
	 * 查询商品分类树形节点
	 */
	List<EasyUiTreeNodeResult> getItemCatList(long parentId);

	/**
	 * 新增商品
	 * @param tbItem 商品表对象
	 * @param desc 商品描述
	 * @return
	 */
	ResponeResult insertItemAndDesc(TbItem tbItem,String desc);
}
