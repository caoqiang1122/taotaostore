package com.taotao.sso.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface RegisterService {

    TaotaoResult checkInfo(String data, int type);
    TaotaoResult register(TbUser user);
}
