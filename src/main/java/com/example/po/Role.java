package com.example.po;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Author:Sphinx
 * Date:2019/03/25 15:47
 * Description:
 */
@Data
@TableName("t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private int roleId;
    @TableField
    private String roleName;
}
