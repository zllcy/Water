package com.zll.water.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Customer;
import com.zll.water.entities.History;
import com.zll.water.entities.Worker;
import com.zll.water.mapper.CustomerMapper;
import com.zll.water.mapper.HistoryMapper;
import com.zll.water.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<History> listHistory() {
        return historyMapper.listHistory();
    }

    @Override
    public PageInfo<History> listHistoryForPage(Integer pageNum) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<History> historyList = this.listHistory();
        PageInfo<History> historyPageInfo = new PageInfo<>(historyList);
        return historyPageInfo;
    }

    @Override
    public List<History> searchHistory(String start, String end) throws ParseException {
        Date startTime = simpleDateFormat.parse(start);
        Date endTime = simpleDateFormat.parse(end);
        return historyMapper.searchHistory(startTime, endTime);
    }

    @Override
    public PageInfo<History> searchHistoryForPage(Integer pageNum, String start, String end) throws ParseException {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<History> historyList = this.searchHistory(start, end);
        PageInfo<History> historyPageInfo = new PageInfo<>(historyList);
        return historyPageInfo;
    }

    @Override
    public int deleteHistoryById(Integer hid) {
        return historyMapper.deleteHistoryById(hid);
    }

    @Override
    public History getHistoryById(Integer hid) {
        return historyMapper.getHistoryById(hid);
    }

    @Override
    public int updateHistory(History history, Integer custId, Integer workerId) {
        Customer customer = new Customer();
        customer.setCid(custId);
        history.setCustomer(customer);
        Worker worker = new Worker();
        worker.setWid(workerId);
        history.setWorker(worker);
        return historyMapper.updateHistory(history);
    }

    /**
     * 添加前需要判断客户水票数量是否足够
     * @param history
     * @param custId
     * @param workerId
     * @return
     */
    @Override
    public int saveHistory(History history, Integer custId, Integer workerId) {
        Integer custTicket = customerMapper.selectCustomerTicket(custId);
        if (custTicket < history.getSendWaterCount()) {
            return 0;
        }
        Customer customer = new Customer();
        customer.setCid(custId);
        history.setCustomer(customer);
        Worker worker = new Worker();
        worker.setWid(workerId);
        history.setWorker(worker);
        return historyMapper.saveHistory(history);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public int batchDeleteHistory(String ids) {
        // 处理前端传来的ids字符串
        ids = ids.replaceFirst(",", "");
        String[] split = StrUtil.split(ids, ",");
        List<Integer> idList = new ArrayList<>();
        for (String s : split) {
            idList.add(Integer.parseInt(s));
        }
        int rows =  historyMapper.batchDeleteHistory(idList);
//        System.out.println(1/0);
        return rows;
    }
}
