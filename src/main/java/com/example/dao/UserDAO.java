package com.example.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.po.User;

/**
 * Author:Sphinx
 * Date:2019/03/25 15:15
 * Description:
 */

public interface UserDAO extends BaseMapper<User> {
    void login(User user);
}
