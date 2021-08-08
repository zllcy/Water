package com.zll.water.mapper;

import com.zll.water.entities.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface HistoryMapper {

    List<History> listHistory();

    List<History> searchHistory(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Delete("delete from history where hid = #{hid}")
    int deleteHistoryById(Integer hid);

    History getHistoryById(Integer hid);

    int updateHistory(History history);

    @Insert("insert into history (cust_id, worker_id, send_water_time, send_water_count) " +
            "values (#{customer.cid}, #{worker.wid}, #{sendWaterTime}, #{sendWaterCount})")
    int saveHistory(History history);

    int batchDeleteHistory(@Param("idList") List<Integer> idList);

    @Select("select hid from history where worker_id = #{wid}")
    int selectHistoryByWid(Integer wid);

    @Select("select hid from history where cust_id = #{cid}")
    int selectHistoryByCid(Integer cid);
}
