package com.zll.water.controller;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Customer;
import com.zll.water.entities.History;
import com.zll.water.entities.Worker;
import com.zll.water.service.CustomerService;
import com.zll.water.service.HistoryService;
import com.zll.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WorkerService workerService;

//    @RequestMapping("/listHistory")
//    public String listHistory(Model model) {
//        List<History> historyList = historyService.listHistory();
//        model.addAttribute("historyList", historyList);
//        return "historyList";
//    }

    @RequestMapping("/listHistoryForPage")
    public String listHistoryForPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageInfo<History> historyPageInfo = historyService.listHistoryForPage(pageNum);
        List<History> historyList = historyPageInfo.getList();
        model.addAttribute("historyList", historyList);
        model.addAttribute("pageInfo", historyPageInfo);

        model.addAttribute("pageData", "listHistory");
        return "historyList";
    }

//    @RequestMapping("/searchHistory")
//    public String searchHistory(String start, String end, Model model) throws ParseException {
//        if (start != "" && end != ""){
//            List<History> historyList = historyService.searchHistory(start, end);
//            model.addAttribute("historyList", historyList);
//            model.addAttribute("start", start);
//            model.addAttribute("end", end);
//            return "historyList";
//        }
//        return "redirect:/history/listHistory";
//    }

    @RequestMapping("/searchHistoryForPage")
    public String searchHistoryForPage(String start, String end,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       Model model) throws ParseException {
        if (start != "" && end != "") {
            PageInfo<History> historyPageInfo = historyService.searchHistoryForPage(pageNum, start, end);
            List<History> historyList = historyPageInfo.getList();
            model.addAttribute("historyList", historyList);
            model.addAttribute("pageInfo", historyPageInfo);
            model.addAttribute("pageData", "searchData");
            model.addAttribute("start", start);
            model.addAttribute("end", end);

            return "historyList";
        }
        return "redirect:/history/listHistoryForPage";
    }

    /**
     * 回显下拉框中的送水工名和客户名，然后跳转到添加页面
     * @return
     */
    @RequestMapping("/preSaveHistory")
    public String preSaveHistory(Model model) {
        List<Customer> customerList = customerService.listCustomer();
        List<Worker> workerList = workerService.listWorker();
        model.addAttribute("customerList", customerList);
        model.addAttribute("workerList", workerList);
        return "historySave";
    }

    @RequestMapping("/saveHistory")
    public String saveHistory(History history, Integer custId, Integer workerId, Model model) {
        int rows = historyService.saveHistory(history, custId, workerId);
        if (rows > 0) {
            return "redirect:/history/listHistoryForPage";
        } else {
            model.addAttribute("fail", "客户水票数量不足");
            return "redirect:/history/listHistoryForPage";
        }
    }

    @RequestMapping("/deleteHistory/{hid}")
    public String deleteHistory(@PathVariable("hid") Integer hid) {
        historyService.deleteHistoryById(hid);
        return "redirect:/history/listHistoryForPage";
    }

    /**
     * 回显送水历史信息，跳转到修改页面
     * @param hid
     * @return
     */
    @RequestMapping("/preUpdateHistory/{hid}")
    public String preUpdateHistory(@PathVariable("hid") Integer hid, Model model) {
        History history = historyService.getHistoryById(hid);
        List<Customer> customerList = customerService.listCustomer();
        List<Worker> workerList = workerService.listWorker();

        model.addAttribute("history", history);
        model.addAttribute("customerList", customerList);
        model.addAttribute("workerList", workerList);
        return "historyUpdate";
    }

    /**
     * 与取、调、转无关的代码下沉到业务逻辑层。
     * @param history
     * @param custId
     * @param workerId
     * @return
     */
    @RequestMapping("/updateHistory")
    public String updateHistory(History history, Integer custId, Integer workerId) {
        historyService.updateHistory(history, custId, workerId);
        return "redirect:/history/listHistoryForPage";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/batchDeleteHistory")
    @ResponseBody
    public String batchDeleteHistory(@RequestParam("ids") String ids) {
        try {
            int rows = historyService.batchDeleteHistory(ids);
            if (rows > 0) {
                return "success";
            } else {
                return "fail";
            }
        }catch (Exception e) {
            log.error("出现异常，回滚事务："+ e);
            return "fail";
        }
    }

}
