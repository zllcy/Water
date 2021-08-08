package com.zll.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    private Integer hid;

    private Worker worker;

    private Customer customer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendWaterTime;

    private Integer sendWaterCount;
}
