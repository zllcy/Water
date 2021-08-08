package com.zll.water.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zll.water.entities.SendWaterInfo;
import com.zll.water.mapper.SendWaterInfoMapper;
import com.zll.water.service.SendWaterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendWaterInfoServiceImpl implements SendWaterInfoService {
    @Autowired
    private SendWaterInfoMapper sendWaterInfoMapper;
    @Override
    public List<SendWaterInfo> countSendWaterInfo() {
        return sendWaterInfoMapper.countSendWaterInfo();
    }

    @Override
    public PageInfo<SendWaterInfo> listSendWaterInfoForPage(Integer pageNum) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<SendWaterInfo> sendWaterInfoList = this.countSendWaterInfo();
        PageInfo<SendWaterInfo> sendWaterInfoPageInfo = new PageInfo<>(sendWaterInfoList);
        return sendWaterInfoPageInfo;
    }

    @Override
    public List<SendWaterInfo> searchSendWaterInfo(String workerName) {
        return sendWaterInfoMapper.searchSendWaterInfo(workerName);
    }

    @Override
    public PageInfo<SendWaterInfo> searchSendWaterInfoForPage(String workerName, Integer pageNum) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<SendWaterInfo> sendWaterInfoList = this.searchSendWaterInfo(workerName);
        PageInfo<SendWaterInfo> sendWaterInfoPageInfo = new PageInfo<>(sendWaterInfoList);
        return sendWaterInfoPageInfo;
    }
}
