package com.zll.water.mapper;

import com.zll.water.entities.SendWaterInfo;

import java.util.List;

public interface SendWaterInfoMapper {
    List<SendWaterInfo> countSendWaterInfo();

    List<SendWaterInfo> searchSendWaterInfo(String workerName);
}
