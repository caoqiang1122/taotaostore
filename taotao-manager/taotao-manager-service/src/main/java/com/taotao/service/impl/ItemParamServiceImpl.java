package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUiDataGridResponse;
import com.taotao.common.bean.EasyUiTreeNodeResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl  implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUiDataGridResponse getItemParamList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        List<TbItemParam> tbItemParamsList = tbItemParamMapper.selectByExample(tbItemParamExample);
        //取分页后的结果
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(tbItemParamsList);
        //将结果返回
        EasyUiDataGridResponse easyUiDataGridResponse = new EasyUiDataGridResponse();
        easyUiDataGridResponse.setTotal(pageInfo.getTotal());
        easyUiDataGridResponse.setRows(tbItemParamsList);
        return easyUiDataGridResponse;
    }
}
