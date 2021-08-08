package com.zll.water.mapper;

import com.zll.water.entities.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface SalaryMapper {

    List<Salary> calFinalWorkerSalary();

    List<Salary> searchSalary(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
