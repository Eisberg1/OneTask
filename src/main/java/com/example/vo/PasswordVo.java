package com.example.vo;

import lombok.Data;

/**
 * Author:Sphinx
 * Date:2019/03/27 15:44
 * Description:
 */
@Data
public class PasswordVo {
    private String password;//密码
    private String salt; //盐值
}
