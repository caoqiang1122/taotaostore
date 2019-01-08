package com.taotao.sso.controller;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title 用户注册
 * @Description: 用户注册
 * @Author caoqiang
 * @Date 2019/1/8 10:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 数据校验
     * @param param
     * @param type
     * @param callback
     * @return
     */
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,@PathVariable Integer type,String callback){
        try {
            TaotaoResult taotaoResult = registerService.checkInfo(param,type);
            if (StringUtils.isNotBlank(callback)){
                //请求为jsonp调用，需要支持
                MappingJacksonValue mappingJacksonValue  = new MappingJacksonValue(taotaoResult);
                mappingJacksonValue.setJsonpFunction(callback);
                return  mappingJacksonValue;
            }
            return taotaoResult;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 注册
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser){
        try {
            return registerService.register(tbUser);
        }catch (Exception e){
            return TaotaoResult.build(500,ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("/page/register")
    public String showRegister(){
        return "register";
    }
}
