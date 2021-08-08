package com.zll.water.mapper;


import com.zll.water.entities.Worker;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WorkerMapper {

    List<Worker> listWorker();

    List<Worker> searchWorker(@Param("workerName") String workerName);

    int saveWorker(Worker worker);

    int deleteWorkerById(Integer wid);

    Worker getWorkerById(Integer wid);

    int updateWorker(Worker worker);

    /**
     * 调整工资
     * @param wid
     * @param workerSalary
     * @return
     */
    @Update("update worker set worker_salary = #{workerSalary} where wid = #{wid}")
    int adjustSalary(@Param("wid") Integer wid, @Param("workerSalary") Integer workerSalary);

    int batchDeleteWorker(@Param("idList") List<Integer> idList);

    @Select("select w.wid, w.worker_name, w.worker_salary, w.worker_money, w.worker_image " +
            "from worker w left join history h on w.wid = h.worker_id " +
            "where h.worker_id is null")
    List<Worker> countWorkerNoSendWater();
}
