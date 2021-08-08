package com.zll.water.controller;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Salary;
import com.zll.water.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

//    @RequestMapping("/listSalary")
//    public String listSalary(Model model) {
//        List<Salary> salaryList = salaryService.calFinalWorkerSalary();
//        model.addAttribute("salaryList", salaryList);
//        return "salaryList";
//    }

    @RequestMapping("/listSalaryForPage")
    public String listSalaryForPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageInfo<Salary> salaryPageInfo = salaryService.listSalaryForPage(pageNum);
        List<Salary> salaryList = salaryPageInfo.getList();

        model.addAttribute("pageInfo", salaryPageInfo);
        model.addAttribute("salaryList", salaryList);

        model.addAttribute("pageData", "listSalary");
        return "salaryList";
    }

//    @RequestMapping("/searchSalary")
//    public String searchSalary(String start, String end, Model model) throws ParseException {
//        if (start != "" && end != "") {
//            List<Salary> salaryList = salaryService.searchSalary(start, end);
//            model.addAttribute("salaryList", salaryList);
//            model.addAttribute("start", start);
//            model.addAttribute("end", end);
//            return "salaryList";
//        }
//        return "redirect:/salary/listSalary";
//    }

    @RequestMapping("/searchSalaryForPage")
    public String searchSalaryForPage(String start, String end,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      Model model) throws ParseException {
        if (start != "" && end != "") {
            PageInfo<Salary> salaryPageInfo = salaryService.searchSalaryForPage(start, end, pageNum);
            List<Salary> salaryList = salaryPageInfo.getList();
            model.addAttribute("salaryList", salaryList);
            model.addAttribute("pageInfo", salaryPageInfo);
            model.addAttribute("start", start);
            model.addAttribute("end", end);
            model.addAttribute("pageData", "searchData");
            return "salaryList";
        }
        return "redirect:/salary/listSalaryForPage";
    }
}
