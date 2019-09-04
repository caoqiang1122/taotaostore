package com.taotao.common.bean;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/4/17 16:18
 * @Version 1.0
 */
public enum  EnumContant {
    login(1,"登录","验证码："),
    register(2,"注册","验证码");

    private int code;
    private String description;
    private String message;

    EnumContant(int code, String description, String message) {
        this.code = code;
        this.description = description;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

