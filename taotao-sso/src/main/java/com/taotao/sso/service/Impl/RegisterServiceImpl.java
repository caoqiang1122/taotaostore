package com.taotao.sso.service.Impl;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Title 用户注册
 * @Description: 用户注册
 * @Author caoqiang
 * @Date 2019/1/8 10:00
 * @Version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 数据有效性校验
     * @param data
     * @param type
     * @return
     */
    @Override
    public TaotaoResult checkInfo(String data, int type) {
        //设置查询条件
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        //用户名
        if (1 == type){
            criteria.andUsernameEqualTo(data);
        //电话
        }else if (2 == type){
            criteria.andPhoneEqualTo(data);
        //Email
        }else if (3 == type){
            criteria.andEmailEqualTo(data);
        }

        List<TbUser> tbUserList = tbUserMapper.selectByExample(tbUserExample);

        //没有查到可以使用
        if (tbUserList == null || tbUserList.isEmpty()){
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public TaotaoResult register(TbUser user) {
    //有效性检查
    if (StringUtils.isBlank(user.getUsername())){
        return TaotaoResult.build(400,"用户名不能为空");
    }
    if (StringUtils.isBlank(user.getPassword())){
        return TaotaoResult.build(400,"密码不能为空");
    }
    //密码进行MD5加密
    String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
    user.setPassword(password);

    //补全user对象信息
    user.setCreated(new Date());
    user.setUpdated(new Date());

    //把用户信息入库
    tbUserMapper.insert(user);

    return TaotaoResult.ok(true);
    }
}
