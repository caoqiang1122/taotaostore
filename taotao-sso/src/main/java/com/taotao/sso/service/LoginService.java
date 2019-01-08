package com.taotao.sso.service;

import com.taotao.common.bean.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
