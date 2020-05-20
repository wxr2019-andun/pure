package com.andun.platform.config.service;

import com.andun.platform.config.shiro.StatelessToken;

/**
 * Author:wuxinrui
 * Date:2020-03-05  16:21
 * Description:
 */

public interface TokenService {

    /**
      *@Author:wuxinrui
      *@Description: 创建token
      *@Data 16:21 2020/3/5
    */
    StatelessToken createToken(String account);
    /**
     *@Author:wuxinrui
     *@Description: 获取 AuthenticationToken对象
     *@Data 16:21 2020/3/5
     */
    StatelessToken getToken(String account);

    /**
     *@Author:wuxinrui
     *@Description: 删除redis中用户凭证
     *@Data 16:21 2020/3/5
     */
    void removeToken(String account);

    /**
     *@Author:wuxinrui
     *@Description: 判断token是否有效
     *@Data 16:21 2020/3/5
     */
    boolean checkToken(StatelessToken statelessToken);
}
