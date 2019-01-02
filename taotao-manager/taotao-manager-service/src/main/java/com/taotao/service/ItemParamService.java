package com.taotao.service;

import com.taotao.common.bean.EasyUiDataGridResponse;

public interface ItemParamService {
    /**
     * 分页查询商品规格参数列表
     * @param page 当前显示的页码
     * @param rows 当前页显示的记录数
     * @return
     */
    EasyUiDataGridResponse getItemParamList(int page, int rows);
}
