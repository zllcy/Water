package com.zll.water.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Worker;
import com.zll.water.mapper.HistoryMapper;
import com.zll.water.mapper.WorkerMapper;
import com.zll.water.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<Worker> listWorker() {
        return workerMapper.listWorker();
    }

    @Override
    public PageInfo<Worker> listWorkerForPage(Integer pageNum) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Worker> workerList = this.listWorker();
        PageInfo<Worker> workerPageInfo = new PageInfo<>(workerList);
        return workerPageInfo;
    }

    @Override
    public List<Worker> searchWorker(String workerName) {
        return workerMapper.searchWorker(workerName);
    }

    @Override
    public PageInfo<Worker> searchWorkerForPage(Integer pageNum, String workerName) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Worker> workerList = this.searchWorker(workerName);
        PageInfo<Worker> workerPageInfo = new PageInfo<>(workerList);
        return workerPageInfo;
    }

    @Override
    public int saveWorker(Worker worker) {
        return workerMapper.saveWorker(worker);
    }

    /**
     * 判断是否为外键
     * @param wid
     * @return
     */
    @Override
    public int deleteWorkerById(Integer wid) {
        if (historyMapper.selectHistoryByWid(wid) > 0) {
            return 0;
        }
        return workerMapper.deleteWorkerById(wid);
    }

    @Override
    public Worker getWorkerById(Integer wid) {
        return workerMapper.getWorkerById(wid);
    }

    @Override
    public int updateWorker(Worker worker) {
        return workerMapper.updateWorker(worker);
    }

    @Override
    public int adjustSalary(Integer wid, Integer workerSalary) {
        return workerMapper.adjustSalary(wid, workerSalary);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,Error.class})
    public int batchDeleteWorker(String ids) {
        // 处理前端传来的ids字符串
        ids = ids.replaceFirst(",", "");
        String[] split = StrUtil.split(ids, ",");
        List<Integer> idList = new ArrayList<>();
        for (String s : split) {
            idList.add(Integer.parseInt(s));
        }
        return workerMapper.batchDeleteWorker(idList);
    }

    /**
     * 分页显示没有送水的送水工
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<Worker> countWorkerNoSendWater(Integer pageNum) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Worker> workerList = workerMapper.countWorkerNoSendWater();
        PageInfo<Worker> workerPageInfo = new PageInfo<>(workerList);
        return workerPageInfo;
    }
}
