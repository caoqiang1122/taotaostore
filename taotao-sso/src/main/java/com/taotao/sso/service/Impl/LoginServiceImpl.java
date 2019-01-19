package com.taotao.sso.service.Impl;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtil;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @Title 单点登录
 * @Description: 单点登录
 * @Author caoqiang
 * @Date 2019/1/8 14:22
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;

    @Value("${REDIS_SESSION_EXPIRE}")
    private Integer REDIS_SESSION_EXPIRE;

    private static String TT_TOKEN="TT_TOKEN";

    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //有效性校验
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return TaotaoResult.build(400,"" +
                    "用户名和密码不能为空");
        }

        //根据用户名查询数据库，然后进行密码校验
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser>  tbUserList = tbUserMapper.selectByExample(tbUserExample);
        if (tbUserList == null || tbUserList.isEmpty()){
            return TaotaoResult.build(400,"用户名或者密码错误");
        }

        TbUser tbUser = tbUserList.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())){
            return TaotaoResult.build(400,"用户名或者密码错误");
        }

        //生成token
        String token = UUID.randomUUID().toString();
        //为了安全将密码清空
        tbUser.setPassword("");

        //将用户信息写入redis
        jedisClient.set(REDIS_SESSION_KEY + ":" +token,JsonUtil.objectToJson(tbUser));
        jedisClient.expire(REDIS_SESSION_KEY + ":" +token,REDIS_SESSION_EXPIRE);

        //将token写入cookie
        CookieUtils.setCookie(request,response,TT_TOKEN,token);

        //返回token
        return TaotaoResult.ok(token);
    }
}
