package com.zll.water.controller;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.SendWaterInfo;
import com.zll.water.service.SendWaterInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/water")
@Slf4j
public class SendWaterInfoController {

    @Autowired
    private SendWaterInfoService sendWaterInfoService;

//    @RequestMapping("/countSendWaterInfo")
//    public String countWaterNumber(Model model) {
//        List<SendWaterInfo> sendWaterInfoList = sendWaterInfoService.countSendWaterInfo();
////        if (log.isInfoEnabled()) {
////            log.info(sendWaterInfoList.toString());
////        }
//        model.addAttribute("sendWaterInfoList", sendWaterInfoList);
//        return "sendWaterInfoList";
//    }

    @RequestMapping("listSendWaterInfoForPage")
    public String listSendWaterInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageInfo<SendWaterInfo> sendWaterInfoPageInfo = sendWaterInfoService.listSendWaterInfoForPage(pageNum);
        List<SendWaterInfo> sendWaterInfoList = sendWaterInfoPageInfo.getList();

        model.addAttribute("sendWaterInfoList", sendWaterInfoList);
        model.addAttribute("pageInfo", sendWaterInfoPageInfo);
        model.addAttribute("pageData", "listSendWaterInfo");

        return "sendWaterInfoList";
    }

//    @RequestMapping("/searchSendWaterInfo")
//    public String searchSendWaterInfo(String workerName, Model model) {
//        List<SendWaterInfo> sendWaterInfoList = sendWaterInfoService.searchSendWaterInfo(workerName);
//        model.addAttribute("sendWaterInfoList", sendWaterInfoList);
//        model.addAttribute("data", workerName);
//        return "sendWaterInfoList";
//    }

    @RequestMapping("/searchSendWaterInfoForPage")
    public String searchSendWaterInfoForPage(String workerName,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             Model model) {
        PageInfo<SendWaterInfo> sendWaterInfoPageInfo = sendWaterInfoService.searchSendWaterInfoForPage(workerName, pageNum);
        List<SendWaterInfo> sendWaterInfoList = sendWaterInfoPageInfo.getList();
        model.addAttribute("pageInfo", sendWaterInfoPageInfo);
        model.addAttribute("sendWaterInfoList", sendWaterInfoList);
        model.addAttribute("data", workerName);

        model.addAttribute("pageData", "searchData");
        return "sendWaterInfoList";
    }
}

