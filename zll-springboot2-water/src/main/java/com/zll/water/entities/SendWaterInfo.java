package com.zll.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendWaterInfo {
    private String workerName;
    private String customerList;
    private Integer sendWaterCount;
}
