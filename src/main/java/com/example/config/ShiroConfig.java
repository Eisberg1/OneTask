package com.example.config;

import com.example.custom.MyRolesAuthorizationFilter;
import com.example.custom.MyShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author:Sphinx
 * Date:2019/03/27 9:14
 * Description:1.配置ShiroFilterFactory 2.配置SecurityManager
 */

@Configuration
public class ShiroConfig {

    @Bean("myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //LinkedHashMap是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        //配置logout过滤器
        filterChainMap.put("/logout", "logout");
        //所有url必须通过认证才可以访问
        //filterChainMap.put("/*", "anno");

        //shiro自定义过滤器 对应shiro_auth的权限
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("/jsp/**",new MyRolesAuthorizationFilter());

        //设置默认登录的url
        shiroFilterFactoryBean.setLoginUrl("/html/login");
        //设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        shiroFilterFactoryBean.setFilters(filterMap);
        //设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean("myShiroFilter")
    public AuthorizationFilter myShiroFilter(){
        return new MyRolesAuthorizationFilter();
    }



}
