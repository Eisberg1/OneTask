package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.dao.UserDAO;
import com.example.po.User;
import com.example.service.UserService;
import com.example.vo.PasswordVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/03/25 15:59
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PasswordService passwordService;
    @Override
    public List<User> selectUsers(){
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.getEntity();
        //List<User> users = userDAO.selectPage(new Page<User>(1, 2), ew);
        List<User> users = userDAO.selectList(ew);
        System.out.println(users);
        return users;
    }

    @Override
    public void login(User user)throws AuthenticationException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getUserPassword());
        try {
            subject.login(token);
            System.out.println("--------------Ok-------------");
        }catch (AuthenticationException e){
            System.out.println("--------------Error-------------");
            throw e;
        }
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public void regist(User user) {
        //将明文密码进行加密
        PasswordVo passwordVo = new PasswordVo();
        passwordVo.setPassword(user.getUserPassword());
        passwordVo.setSalt(user.getUserName());
        String encryptPassword = passwordService.encryptPassword(passwordVo);
        user.setUserPassword(encryptPassword);
        userDAO.insert(user);
    }


}
