package com.example.service;

import com.example.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/03/25 15:58
 * Description:
 */
public interface UserService{
    List<User> selectUsers();

    void login(User user);

    void logout();

    void regist(User user);

}
