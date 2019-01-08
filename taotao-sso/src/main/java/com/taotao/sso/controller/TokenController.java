package com.taotao.sso.controller;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title 根据token在redis中查询用户信息
 * @Description: 根据token在redis中查询用户信息
 * @Author caoqiang
 * @Date 2019/1/8 15:20
 * @Version 1.0
 */

@Controller
@RequestMapping("/user")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/token/{token}",method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String callback){
        TaotaoResult taotaoResult = tokenService.getUserByToken(token);
        if (StringUtils.isBlank(callback)){
            return taotaoResult;
        }

        //支持jsonp
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taotaoResult);
        mappingJacksonValue.setJsonpFunction(callback);

        return  mappingJacksonValue;
    }
}
