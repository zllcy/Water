package com.zll.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private Integer wid;
    private String workerName;
    private Integer workerSalary;
    private Double workerMoney;
    private String workerImage;
}
