package com.taotao.common.bean;

import java.util.List;

/**
 * easyUI的dataGrid分页控件的返回值bean
 * */

public class EasyUiDataGridResponse {
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 数据集合
	 */
	private List<?> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
