package com.taotao.sso.service;

import com.taotao.common.bean.TaotaoResult;

public interface TokenService {
    /**
     * 根据token在redis中查询用户信息
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);
}
