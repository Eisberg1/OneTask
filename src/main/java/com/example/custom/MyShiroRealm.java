package com.example.custom;

import com.example.po.User;
import com.example.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author:Sphinx
 * Date:2019/03/27 10:37
 * Description:
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection p) {
        User user = (User) p.getPrimaryPrincipal();
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("AuthenticationInfo++++++++++++++++++++++");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        //if (StringUtils.isBlank(username)) {
        //    throw new UnknownAccountException("账号不能为空");
        //}
        //if (StringUtils.isBlank(password)) {
        //    throw new IncorrectCredentialsException("密码不能为空");
        //}
        //User u = new User();
        //u.setUserName(username);
        //u.setUserPassword(password);
        //userService.login(u);
        //if (Objects.isNull(user)){
        //    throw new UnknownAccountException("账号不存在");
        //}
        return new SimpleAuthenticationInfo(
                user,    //认证通过后，存放在session,一般存放user对象
                password,//用户数据库中的密码
                ByteSource.Util.bytes(username),//盐值
                getName()//返回Realm名
        );
    }


}
