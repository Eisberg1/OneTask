package com.example.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.po.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/09 10:07
 * Description:
 */
public interface ReportDAO extends BaseMapper<Report> {
    int insertBatch(List<Report> list);
}
