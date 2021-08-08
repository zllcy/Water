package com.zll.water.service;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.History;

import java.text.ParseException;
import java.util.List;

public interface HistoryService {
    int PAGE_SiZE = 3;

    List<History> listHistory();

    PageInfo<History> listHistoryForPage(Integer pageNum);

    List<History> searchHistory(String start, String end) throws ParseException;

    PageInfo<History> searchHistoryForPage(Integer pageNum, String start, String end) throws ParseException;

    int deleteHistoryById(Integer hid);

    History getHistoryById(Integer hid);

    int updateHistory(History history, Integer custId, Integer workerId);

    int saveHistory(History history, Integer custId, Integer workerId);

    int batchDeleteHistory(String ids);
}
