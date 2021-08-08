package com.zll.water.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Salary;
import com.zll.water.mapper.SalaryMapper;
import com.zll.water.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Salary> calFinalWorkerSalary() {
        return salaryMapper.calFinalWorkerSalary();
    }

    @Override
    public PageInfo<Salary> listSalaryForPage(Integer pageNum) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Salary> salaryList = this.calFinalWorkerSalary();
        PageInfo<Salary> salaryPageInfo = new PageInfo<>(salaryList);
        return salaryPageInfo;
    }

    @Override
    public List<Salary> searchSalary(String start, String end) throws ParseException {
        Date startTime = simpleDateFormat.parse(start);
        Date endTime = simpleDateFormat.parse(end);
        return salaryMapper.searchSalary(startTime, endTime);
    }

    @Override
    public PageInfo<Salary> searchSalaryForPage(String start, String end, Integer pageNum) throws ParseException {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Salary> salaryList = this.searchSalary(start, end);

        PageInfo<Salary> salaryPageInfo = new PageInfo<>(salaryList);

        return salaryPageInfo;
    }
}
