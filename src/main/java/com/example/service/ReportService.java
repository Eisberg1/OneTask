package com.example.service;

import com.example.po.Report;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/09 10:09
 * Description:
 */
public interface ReportService {
    public int insert(Report report);

    public int insert(List<Report> list);

    public List<Report> selectAll();
}
