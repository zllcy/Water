package com.zll.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    private String workerName;
    private Integer workerSalary;
    private Double workerMoney;
    private Integer sendWaterCount;
    private Double finalWorkerSalary;
}
