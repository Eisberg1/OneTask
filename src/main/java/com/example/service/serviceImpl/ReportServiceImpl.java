package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.dao.ReportDAO;
import com.example.po.Report;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/09 10:09
 * Description:
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDAO reportDAO;

    public int insert(Report report){
      return reportDAO.insert(report);
    }

    @Override
    public int insert(List<Report> list) {
        int count = reportDAO.insertBatch(list);
        return count;
    }

    @Override
    public List<Report> selectAll() {
        return reportDAO.selectList(new EntityWrapper<Report>());
    }


}
