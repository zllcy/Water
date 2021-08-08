package com.zll.water.service;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.SendWaterInfo;

import java.util.List;

public interface SendWaterInfoService {
    int PAGE_SiZE = 3;

    List<SendWaterInfo> countSendWaterInfo();

    PageInfo<SendWaterInfo> listSendWaterInfoForPage(Integer pageNum);

    List<SendWaterInfo> searchSendWaterInfo(String workerName);

    PageInfo<SendWaterInfo> searchSendWaterInfoForPage(String workerName, Integer pageNum);
}
