package com.andun.platform.config.service.impl;

import com.andun.common.AppConstant;
import com.andun.common.util.TokenUtil;
import com.andun.platform.config.service.TokenService;
import com.andun.platform.config.shiro.StatelessToken;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author:wuxinrui
 * Date:2020-03-05  17:16
 * Description:
 */
@Service
public class TokenImpl implements TokenService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public StatelessToken createToken(String account) {
        //获取 生成的秘钥
        String token = TokenUtil.genToken();
        //创建 StatelessToken类
        StatelessToken statelessToken = new StatelessToken(account,token);
        //向redis 存入用户秘钥
        stringRedisTemplate.opsForValue().set(AppConstant.TOKEN+account,token);
        return statelessToken;
    }

    @Override
    public StatelessToken getToken(String account) {
        //获取redis中已经创建的用户token数据
        String token = stringRedisTemplate.opsForValue().get(AppConstant.TOKEN+account);
        //创建一个 StatelessToken类
        StatelessToken statelessToken = new StatelessToken(account,token);
        return statelessToken;
    }

    @Override
    public void removeToken(String account) {
        stringRedisTemplate.delete(AppConstant.TOKEN+account);
    }

    @Override
    public boolean checkToken(StatelessToken statelessToken) {
        //获取redis 加密后的token
        String token = stringRedisTemplate.opsForValue().get(AppConstant.TOKEN+statelessToken.getAccount());
        // 与 token类中的token比较 相同返回true
        if(statelessToken.getToken().equals(token)){
            return true;
        }
        return false;
    }
}
