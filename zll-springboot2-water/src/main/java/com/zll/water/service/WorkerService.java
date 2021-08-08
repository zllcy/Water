package com.zll.water.service;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Worker;

import java.util.List;

public interface WorkerService {

    /**
     * 每页数量
     */
    int PAGE_SiZE = 3;

    List<Worker> listWorker();

    PageInfo<Worker> listWorkerForPage(Integer pageNum);

    List<Worker> searchWorker(String workerName);

    PageInfo<Worker> searchWorkerForPage(Integer pageNum, String workerName);

    int saveWorker(Worker worker);

    int deleteWorkerById(Integer wid);

    Worker getWorkerById(Integer wid);

    int updateWorker(Worker worker);

    int adjustSalary( Integer wid, Integer workerSalary);

    int batchDeleteWorker(String ids);

    PageInfo<Worker> countWorkerNoSendWater(Integer pageNum);
}
