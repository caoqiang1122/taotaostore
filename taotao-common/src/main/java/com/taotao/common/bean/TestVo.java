package com.taotao.common.bean;

import java.util.List;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/5/27 16:54
 * @Version 1.0
 */
public class TestVo{
    private String RESULT_CODE;
    private String RESULT_MSG;
    private List<TestVo1> testVo1List;

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }

    public String getRESULT_MSG() {
        return RESULT_MSG;
    }

    public void setRESULT_MSG(String RESULT_MSG) {
        this.RESULT_MSG = RESULT_MSG;
    }

    public List<TestVo1> getTestVo1List() {
        return testVo1List;
    }

    public void setTestVo1List(List<TestVo1> testVo1List) {
        this.testVo1List = testVo1List;
    }
}
