package com.taotao.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.TestVo;
import com.taotao.common.bean.TestVo1;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义响应结构
 */
public class JsonUtil {
    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转化为json字符串
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json对象转化为pojo对象list
     *
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructType(List.class, beanType);
        try {
//            List<T> list = MAPPER.readValue(jsonData, javaType);
            List<T> list = MAPPER.readValue(jsonData, new TypeReference<List<T>>(){});
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String jsonData = "{\n" +
                "    \"FUNCTION_LIST\": [\n" +
                "        {\n" +
                "            \"FUNCTION\": [\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/index/index.html\",\n" +
                "                    \"FUNC_NAME\": \"首页\",\n" +
                "                    \"FUNC_IMG\": \"\uE6B2\",\n" +
                "                    \"FUNC_PARENT_ID\": \"-1\",\n" +
                "                    \"FUNC_CODE\": \"HOME\",\n" +
                "                    \"ENT_CLASS_ID\": \"8756\",\n" +
                "                    \"FUNC_ID\": \"25035932\",\n" +
                "                    \"FUNC_SEQ\": \"1\",\n" +
                "                    \"FUNC_LEVEL\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"\",\n" +
                "                    \"FUNC_NAME\": \"商品管理\",\n" +
                "                    \"FUNC_IMG\": \"\uE774\",\n" +
                "                    \"FUNC_PARENT_ID\": \"-1\",\n" +
                "                    \"FUNC_CODE\": \"PRODMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"871\",\n" +
                "                    \"FUNC_ID\": \"25035933\",\n" +
                "                    \"FUNC_SEQ\": \"2\",\n" +
                "                    \"FUNC_LEVEL\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"\",\n" +
                "                    \"FUNC_NAME\": \"活动管理\",\n" +
                "                    \"FUNC_IMG\": \"\uE776\",\n" +
                "                    \"FUNC_PARENT_ID\": \"-1\",\n" +
                "                    \"FUNC_CODE\": \"ACTIVMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"872\",\n" +
                "                    \"FUNC_ID\": \"25035938\",\n" +
                "                    \"FUNC_SEQ\": \"3\",\n" +
                "                    \"FUNC_LEVEL\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"\",\n" +
                "                    \"FUNC_NAME\": \"零售订单\",\n" +
                "                    \"FUNC_IMG\": \"\uE77C\",\n" +
                "                    \"FUNC_PARENT_ID\": \"-1\",\n" +
                "                    \"FUNC_CODE\": \"SALEORDER\",\n" +
                "                    \"ENT_CLASS_ID\": \"873\",\n" +
                "                    \"FUNC_ID\": \"25035934\",\n" +
                "                    \"FUNC_SEQ\": \"4\",\n" +
                "                    \"FUNC_LEVEL\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"\",\n" +
                "                    \"FUNC_NAME\": \"统计分析\",\n" +
                "                    \"FUNC_IMG\": \"\uE77A\",\n" +
                "                    \"FUNC_PARENT_ID\": \"-1\",\n" +
                "                    \"FUNC_CODE\": \"BIMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"875\",\n" +
                "                    \"FUNC_ID\": \"25035935\",\n" +
                "                    \"FUNC_SEQ\": \"5\",\n" +
                "                    \"FUNC_LEVEL\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/goods/goodsManager.html\",\n" +
                "                    \"FUNC_NAME\": \"商品上下架\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035933\",\n" +
                "                    \"FUNC_CODE\": \"SXJMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"8711\",\n" +
                "                    \"FUNC_ID\": \"25035936\",\n" +
                "                    \"FUNC_SEQ\": \"1\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/goods/goodStockQuery.html\",\n" +
                "                    \"FUNC_NAME\": \"商品库存查询\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035933\",\n" +
                "                    \"FUNC_CODE\": \"STOCK\",\n" +
                "                    \"ENT_CLASS_ID\": \"8757\",\n" +
                "                    \"FUNC_ID\": \"25035937\",\n" +
                "                    \"FUNC_SEQ\": \"2\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/goods/sysOutGoods.html\",\n" +
                "                    \"FUNC_NAME\": \"体外产品管理\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035933\",\n" +
                "                    \"FUNC_CODE\": \"TWCPGL\",\n" +
                "                    \"ENT_CLASS_ID\": \"1\",\n" +
                "                    \"FUNC_ID\": \"25036687\",\n" +
                "                    \"FUNC_SEQ\": \"3\",\n" +
                "                    \"FUNC_LEVEL\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/sale/saleManage.html\",\n" +
                "                    \"FUNC_NAME\": \"零售管理\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035934\",\n" +
                "                    \"FUNC_CODE\": \"SALEMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"8758\",\n" +
                "                    \"FUNC_ID\": \"25035941\",\n" +
                "                    \"FUNC_SEQ\": \"1\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/orders/orderManager.html\",\n" +
                "                    \"FUNC_NAME\": \"订单管理\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035934\",\n" +
                "                    \"FUNC_CODE\": \"ORDERMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"874\",\n" +
                "                    \"FUNC_ID\": \"25035942\",\n" +
                "                    \"FUNC_SEQ\": \"2\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/saleTable/saleOrderTable.html\",\n" +
                "                    \"FUNC_NAME\": \"销售清单\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035935\",\n" +
                "                    \"FUNC_CODE\": \"SALELIST\",\n" +
                "                    \"ENT_CLASS_ID\": \"8751\",\n" +
                "                    \"FUNC_ID\": \"25035943\",\n" +
                "                    \"FUNC_SEQ\": \"1\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/saleTable/salePayTable.html\",\n" +
                "                    \"FUNC_NAME\": \"收银清单\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035935\",\n" +
                "                    \"FUNC_CODE\": \"CHECKLIST\",\n" +
                "                    \"ENT_CLASS_ID\": \"8752\",\n" +
                "                    \"FUNC_ID\": \"25035944\",\n" +
                "                    \"FUNC_SEQ\": \"2\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/saleTable/saleOrderReport.html\",\n" +
                "                    \"FUNC_NAME\": \"销售报表\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035935\",\n" +
                "                    \"FUNC_CODE\": \"SALEREPORT\",\n" +
                "                    \"ENT_CLASS_ID\": \"8753\",\n" +
                "                    \"FUNC_ID\": \"25035945\",\n" +
                "                    \"FUNC_SEQ\": \"3\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/saleTable/salePayReport.html\",\n" +
                "                    \"FUNC_NAME\": \"收银报表\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035935\",\n" +
                "                    \"FUNC_CODE\": \"CHECKREPORT\",\n" +
                "                    \"ENT_CLASS_ID\": \"8754\",\n" +
                "                    \"FUNC_ID\": \"25035946\",\n" +
                "                    \"FUNC_SEQ\": \"4\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/export/exportQuery.html\",\n" +
                "                    \"FUNC_NAME\": \"导出列表\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035935\",\n" +
                "                    \"FUNC_CODE\": \"REPORT\",\n" +
                "                    \"ENT_CLASS_ID\": \"8760\",\n" +
                "                    \"FUNC_ID\": \"25035968\",\n" +
                "                    \"FUNC_SEQ\": \"6\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/activityManager.html\",\n" +
                "                    \"FUNC_NAME\": \"活动管理\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035938\",\n" +
                "                    \"FUNC_CODE\": \"HDMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"8721\",\n" +
                "                    \"FUNC_ID\": \"25035939\",\n" +
                "                    \"FUNC_SEQ\": \"1\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/giftManager.html\",\n" +
                "                    \"FUNC_NAME\": \"赠品管理\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035938\",\n" +
                "                    \"FUNC_CODE\": \"ZPMANAGE\",\n" +
                "                    \"ENT_CLASS_ID\": \"8712\",\n" +
                "                    \"FUNC_ID\": \"25035940\",\n" +
                "                    \"FUNC_SEQ\": \"2\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/couponQuery.html\",\n" +
                "                    \"FUNC_NAME\": \"卡劵查询\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035938\",\n" +
                "                    \"FUNC_CODE\": \"KJCX\",\n" +
                "                    \"ENT_CLASS_ID\": \"1\",\n" +
                "                    \"FUNC_ID\": \"25037257\",\n" +
                "                    \"FUNC_SEQ\": \"3\",\n" +
                "                    \"FUNC_LEVEL\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/addActivity.html\",\n" +
                "                    \"FUNC_NAME\": \"活动新增\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035939\",\n" +
                "                    \"FUNC_CODE\": \"HDMANAGER-ADD\",\n" +
                "                    \"ENT_CLASS_ID\": \"8756\",\n" +
                "                    \"FUNC_ID\": \"25035963\",\n" +
                "                    \"FUNC_SEQ\": \"3\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/activityRecord.html\",\n" +
                "                    \"FUNC_NAME\": \"活动记录\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035939\",\n" +
                "                    \"FUNC_CODE\": \"HDMANAGER-RECORD\",\n" +
                "                    \"ENT_CLASS_ID\": \"8757\",\n" +
                "                    \"FUNC_ID\": \"25035964\",\n" +
                "                    \"FUNC_SEQ\": \"4\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/modifyActivity.html\",\n" +
                "                    \"FUNC_NAME\": \"活动编辑\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035939\",\n" +
                "                    \"FUNC_CODE\": \"HDMANAGER-EDIT\",\n" +
                "                    \"ENT_CLASS_ID\": \"8758\",\n" +
                "                    \"FUNC_ID\": \"25035965\",\n" +
                "                    \"FUNC_SEQ\": \"5\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/activities/activityBaseInfo.html\",\n" +
                "                    \"FUNC_NAME\": \"活动查看\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035939\",\n" +
                "                    \"FUNC_CODE\": \"HDMANAGE-VIEW\",\n" +
                "                    \"ENT_CLASS_ID\": \"8755\",\n" +
                "                    \"FUNC_ID\": \"25035966\",\n" +
                "                    \"FUNC_SEQ\": \"6\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/orders/applyReturnGoods.html\",\n" +
                "                    \"FUNC_NAME\": \"申请退货\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035942\",\n" +
                "                    \"FUNC_CODE\": \"APPLYRETURNGOODS\",\n" +
                "                    \"ENT_CLASS_ID\": \"8756\",\n" +
                "                    \"FUNC_ID\": \"25035969\",\n" +
                "                    \"FUNC_SEQ\": \"3\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"VIEW_NAME\": \"/page/saleTable/categoryParticular.html\",\n" +
                "                    \"FUNC_NAME\": \"类目明细报表\",\n" +
                "                    \"FUNC_IMG\": \"\",\n" +
                "                    \"FUNC_PARENT_ID\": \"25035945\",\n" +
                "                    \"FUNC_CODE\": \"SALEREPORT-CATEPATR\",\n" +
                "                    \"ENT_CLASS_ID\": \"8759\",\n" +
                "                    \"FUNC_ID\": \"25035967\",\n" +
                "                    \"FUNC_SEQ\": \"5\",\n" +
                "                    \"FUNC_LEVEL\": \"3\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"RESULT_CODE\": \"0\",\n" +
                "    \"RESULT_MSG\": \"请求成功\"\n" +
                "}";

//        List<TestVo1> list =  jsonToList(jsonData,TestVo1.class);
//        String aa = list.toString();
//        List<TestVo1> list1 =  jsonToList(aa,TestVo1.class);
//        System.out.println(jsonData);
//        System.out.println(list);
//        System.out.println(list.size());
//        System.out.println(list.get(0));
//        TestVo1 testVo1 = new TestVo1();

        try {
            Map m = MAPPER.readValue(jsonData, Map.class);
            System.out.println(m.get("FUNCTION"));
//            testVo1 = jsonToPojo(MAPPER.writeValueAsString(m),TestVo1.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(testVo1);
//        System.out.println(testVo1.getVIEW_NAME());
//        JSONObject jsonObj = new JSONObject(OuterListObj);
//        JSON.parseObject(JSON.toJSONString(dayResultList.get(i)),AssessmentDayVo.class)
//        JSONObject jsonObject=JSONObject.fromObject(objectStr);

//        String json1 = objectToJson(list.get(0));
//        String name = jsonToPojo(json1,TestVo1);

//        System.out.println(list.get(0).getVIEW_NAME());

//        System.out.println(list1.get(0));

    }
}