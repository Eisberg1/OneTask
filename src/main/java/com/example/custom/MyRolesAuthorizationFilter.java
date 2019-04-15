package com.example.custom;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/03/27 17:13
 * Description:
 */

public class MyRolesAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse res, Object o) throws Exception {
        Subject subject = getSubject(req, res);
        //配置的访问所需角色
        String[] rolesArray= (String[]) o;
        if (rolesArray==null||rolesArray.length==0){
            return true;
        }
        //需要修改的地方在这里，判断访问所需的角色是否包含当前用户的角色
        List<String> list = CollectionUtils.asList(rolesArray);
        boolean[] booleans = subject.hasRoles(list);
        for (boolean b:booleans){
            if (b){
                return true;
            }
        }
        return false;
    }
}
