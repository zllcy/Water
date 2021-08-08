package com.zll.water.service;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Salary;
import java.text.ParseException;
import java.util.List;

public interface SalaryService {
    int PAGE_SiZE = 3;

    List<Salary> calFinalWorkerSalary();

    PageInfo<Salary> listSalaryForPage(Integer pageNum);

    List<Salary> searchSalary(String start, String end) throws ParseException;

    PageInfo<Salary> searchSalaryForPage(String start, String end, Integer pageNum) throws ParseException;

}
