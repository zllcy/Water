package com.zll.water.controller;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Customer;
import com.zll.water.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    @RequestMapping("/listCustomer")
//    public String listCustomer(Model model) {
//        List<Customer> customerList = customerService.listCustomer();
//        model.addAttribute("custList", customerList);
//        return "custList";
//    }

    /**
     * 分页查询列表
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/listCustomerForPage")
    public String listCustomerForPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        // 调用业务逻辑层，获取分页数据
        PageInfo<Customer> pageInfo = customerService.listCustomerForPage(pageNum);
        // 获取当前页的客户列表
        List<Customer> custList = pageInfo.getList();
        // 客户列表、分页对象传入前端页面
        model.addAttribute("custList", custList);
        model.addAttribute("pageInfo", pageInfo);
        // 表示普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData", "listCustomer");
        return "custList";
    }

//    @RequestMapping("/searchCustomer")
//    public String searchCustomer(String custName, Model model) {
//        List<Customer> customerList = customerService.searchCustomer(custName);
//        model.addAttribute("custList", customerList);
//
//        model.addAttribute("data", custName);
//        return "custList";
//    }

    /**
     * 搜索分页
     *
     * @param custName
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/searchCustomerForPage")
    public String searchCustomerForPage(
            String custName,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            Model model) {

        PageInfo<Customer> pageInfo = customerService.searchCustomerForPage(pageNum, custName);
        // 数据传入到前端
        model.addAttribute("custList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        // 按条件搜索分页查询
        model.addAttribute("pageData", "searchData");
        model.addAttribute("data", custName);

        // 跳转到客户列表页面
        return "custList";
    }


    @RequestMapping("/preSaveCustomer")
    public String preSaveCustomer() {
        return "custSave";
    }

    /**
     * 返回值需要重定向，只是到custList显示不出数据。
     *
     * @param customer
     * @return
     */
    @RequestMapping("/saveCustomer")
    public String saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/cust/listCustomerForPage";
    }

    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public String deleteCustomer(@RequestParam("cid") Integer cid) {
        int rows = customerService.deleteCustomerById(cid);
        if (rows > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/preUpdateCustomer/{cid}")
    public String preUpdateCustomer(@PathVariable("cid") Integer cid, Model model) {
        Customer customer = customerService.getCustomerById(cid);
        model.addAttribute("cust", customer);
        return "custUpdate";
    }

    @RequestMapping("/updateCustomer")
    public String updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/cust/listCustomerForPage";
    }

    @RequestMapping("/batchDeleteCustomer")
    @ResponseBody
    public String batchDeleteCustomer(@RequestParam("ids") String ids) {
        try{
            int rows = customerService.batchDeleteCustomer(ids);
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
