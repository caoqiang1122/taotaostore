package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUiDataGridResponse;
import com.taotao.common.bean.EasyUiTreeNodeResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

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
}
