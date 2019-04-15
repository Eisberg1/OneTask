package com.example.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Author:Sphinx
 * Date:2019/03/25 14:35
 * Description:
 */
@Data
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private int userId;
    @TableField
    private String userName;
    @TableField
    private String userPassword;
}
