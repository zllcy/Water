package com.zll.water.service;

import com.zll.water.entities.Salary;
import com.zll.water.service.impl.SalaryServiceImpl;
import org.junit.Test;

import java.util.List;

public class calFinalWorkerSalaryTest {
    private SalaryService salaryService = new SalaryServiceImpl();
    @Test
    public void test1() {
        List<Salary> salaryList = salaryService.calFinalWorkerSalary();
        System.out.println(salaryList);
    }
}
