package com.taotao.sso.controller;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title 单点登录
 * @Description: 单点登录
 * @Author caoqiang
 * @Date 2019/1/8 14:12
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password,HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = loginService.login(username, password, request, response);
        return result;
    }

    /**
     * 页面跳转
     * @param redirect
     * @param model
     * @return
     */
    @RequestMapping("/page/login")
    public String showPage(String redirect, Model model){
        //把url参数传递到jsp
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
