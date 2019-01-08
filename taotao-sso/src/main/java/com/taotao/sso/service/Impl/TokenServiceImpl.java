package com.taotao.sso.service.Impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.utils.JsonUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/1/8 15:09
 * @Version 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;

    @Value("${REDIS_SESSION_EXPIRE}")
    private Integer REDIS_SESSION_EXPIRE;

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
        //没有取到用户信息
        if (StringUtils.isBlank(json)){
            return TaotaoResult.build(400,"Session已经过期");
        }
        //取到用户信息
        TbUser tbUser = JsonUtil.jsonToPojo(json,TbUser.class);

        //更新session的过期时间
        jedisClient.expire(REDIS_SESSION_KEY + ":" + token,REDIS_SESSION_EXPIRE);

        //返回用户信息
        return TaotaoResult.ok(tbUser);
    }
}
