package com.example.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author:Sphinx
 * Date:2019/04/08 16:42
 * Description:
 */
@TableName("t_report")
@Data
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private int rId;
    @TableField
    private String rName;
    @TableField
    private float rY;
    @TableField
    private Date rX;

}
