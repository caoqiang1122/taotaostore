package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUiDataGridResponse;
import com.taotao.common.bean.EasyUiTreeNodeResult;
import com.taotao.common.utils.IdUtil;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.*;
import com.taotao.vo.ResponeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.taotao.mapper.TbItemMapper;
import com.taotao.service.ItemService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Override
	public TbItem getItemById(Long itemId) {
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}

	@Override
	public EasyUiDataGridResponse getItemList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page,rows);
		//执行查询
		TbItemExample tbItemExample = new TbItemExample();
		List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
		//取分页后的结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
		EasyUiDataGridResponse easyUiDataGridResponse = new EasyUiDataGridResponse();
		easyUiDataGridResponse.setTotal(pageInfo.getTotal());
		easyUiDataGridResponse.setRows(tbItemList);
		return easyUiDataGridResponse;
	}

	/**
	 * 新增商品的商品目录选择
	 * @param parentId
	 * @return
	 */
	@Override
	public List<EasyUiTreeNodeResult> getItemCatList(long parentId) {
		//根据parentId查询分类列表
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		//设置查询条件
		TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		//转化为easyUI的node列表
		List<EasyUiTreeNodeResult> easyUiTreeNodeResultList = new ArrayList<>();
		for (TbItemCat tbItemCat: tbItemCatList) {
			EasyUiTreeNodeResult easyUiTreeNodeResult = new EasyUiTreeNodeResult();
			easyUiTreeNodeResult.setId(tbItemCat.getId());
			easyUiTreeNodeResult.setText(tbItemCat.getName());
			easyUiTreeNodeResult.setState(tbItemCat.getIsParent()?"closed":"open");
			easyUiTreeNodeResultList.add(easyUiTreeNodeResult);
		}
		return easyUiTreeNodeResultList;
	}

	/**
	 * 添加商品信息
	 * @param tbItem 商品表对象
	 * @param desc 商品描述
	 * @return
	 */
	@Override
	public ResponeResult insertItemAndDesc(TbItem tbItem, String desc) {
		//生成商品id
		long itemId = IdUtil.genItemId();
		tbItem.setId(itemId);
		//1-正常 2-下架 3-删除
		tbItem.setStatus((byte)1);
		//创建时间和更新时间
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		//插入商品表
		tbItemMapper.insert(tbItem);

		//商品描述
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDescMapper.insert(tbItemDesc);

		//返回结果值
		return ResponeResult.ok();
	}
}
