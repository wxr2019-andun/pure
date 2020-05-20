package com.andun.platform.config.shiro;

import com.alibaba.fastjson.JSON;
import com.andun.platform.config.service.TokenService;
import com.andun.platform.config.service.impl.TokenImpl;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author:wuxinrui
 * Date:2020-03-05  17:52
 * Description: shiro环境设置
 */

@Configuration
public class ShiroConfiguration {
    private final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);


    @Bean
    public TokenService tokenManager() {
        logger.info("ShiroConfig.getTokenManager()");
        //默认的token管理实现类 32位uuid
        TokenImpl tokenManager = new TokenImpl();
        return tokenManager;
    }

    //缓存设置
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        //具体设置在xml中配置  EhCacheManager读取xml文件
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    //设置域
    @Bean(name = "AuthRealm")
    public AuthRealm authRealm(TokenService tokenManager, EhCacheManager cacheManager) {
        AuthRealm realm = new AuthRealm();
        realm.setCacheManager(cacheManager);
        realm.setTokenManager(tokenManager);
        return realm;
    }

    //   生命周期Bean后处理器
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //默认顾问自动代理创建者
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    //默认Web安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(authRealm);
        // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());
        return defaultWebSecurityManager;
    }


    //授权属性源顾问
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    //加载Shiro过滤器链
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        //过滤器链定义映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/*.html", "anon");
        filterChainDefinitionMap.put("/*.js", "anon");
        filterChainDefinitionMap.put("/*.css", "anon");
        filterChainDefinitionMap.put("/resource/**", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");
        filterChainDefinitionMap.put("/dist/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/api/user/**", "anon");
        filterChainDefinitionMap.put("/api/sys/manager/query/h5", "anon");
        filterChainDefinitionMap.put("/api/sys/doctorServiceHistory/selectDoctorServiceHistoryInfo", "anon");
        filterChainDefinitionMap.put("/api/health/h5", "anon");
        filterChainDefinitionMap.put("/api/health/organ", "anon");
        filterChainDefinitionMap.put("/api/health/report/query", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/management/login", "anon");
        filterChainDefinitionMap.put("/api/common/**", "anon");

        //设置不被拦截的接口
        filterChainDefinitionMap.put("/one/**", "anon");

        //filterChainDefinitionMap.put("/**", "anon");
        // anon：它对应的过滤器里面是空的,什么都没做
        logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");

        filterChainDefinitionMap.put("/**", "statelessAuthc");
        logger.debug("[拦截链] - [{}]", JSON.toJSONString(filterChainDefinitionMap));
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    //访问过滤
    private StatelessAuthcFilter statelessAuthcFilter(com.andun.platform.config.service.TokenService tokenManager) {
        logger.info("ShiroConfig-statelessAuthcFilter");
        StatelessAuthcFilter statelessAuthcFilter = new StatelessAuthcFilter();
        statelessAuthcFilter.setTokenManager(tokenManager);
        return statelessAuthcFilter;
    }

    //Shiro过滤器工厂
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, TokenService tokenManager) {
        //Shiro过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //set 安全管理
        shiroFilterFactoryBean.setSecurityManager(securityManager);



        //创建访问过滤 传token服务
        StatelessAuthcFilter statelessAuthcFilter = statelessAuthcFilter(tokenManager);


        //    创建map将访问获取 放入v键
        Map<String, Filter> filter = new HashMap<>();
        filter.put("statelessAuthc", statelessAuthcFilter);
        //过滤器工厂 设置监听器
        shiroFilterFactoryBean.setFilters(filter);
        //   将Shiro过滤器工厂放入   加载Shiro过滤器链
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }


}
