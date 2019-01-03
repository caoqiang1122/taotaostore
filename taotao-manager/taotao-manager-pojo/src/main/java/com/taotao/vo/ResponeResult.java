package com.taotao.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

public class ResponeResult {
    //定义jackson对象
    private static final ObjectMapper MAPPER= new ObjectMapper();

    //相应业务状态
    private Integer status;

    //响应消息
    private String msg;

    //响应中的数据
    private Object data;

    public static ResponeResult build(Integer status, String msg, Object data){
        return new ResponeResult(status,msg,data);
    }

    public static ResponeResult build(Integer status,String msg){
        return new ResponeResult(status,msg,null);
    }

    public static ResponeResult ok(Object data){
        return new ResponeResult(data);
    }

    public static ResponeResult ok(){
        return new ResponeResult(null);
    }

    public ResponeResult() {

    }

    public ResponeResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponeResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
