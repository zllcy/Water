package com.zll.water.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Worker;
import com.zll.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    /**
     * 服务器上传图片路径
     */
    @Value("${location}")
    private String location;

//    @RequestMapping("/listWorker")
//    public String listWorker(Model model) {
//        List<Worker> workerList = workerService.listWorker();
//        model.addAttribute("workerList", workerList);
//        return "workerList";
//    }

    @RequestMapping("/listWorkerForPage")
    public String listWorkerForPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageInfo<Worker> workerPageInfo = workerService.listWorkerForPage(pageNum);
        // 获取当前页的工人列表
        List<Worker> workerList = workerPageInfo.getList();
        model.addAttribute("workerList", workerList);
        model.addAttribute("pageInfo", workerPageInfo);
        // 表示普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData", "listWorker");
        return "workerList";
    }

//    @RequestMapping("/searchWorker")
//    public String searchWorker(String workerName, Model model) {
//        List<Worker> workerList = workerService.searchWorker(workerName);
//        model.addAttribute("workerList", workerList);
//        model.addAttribute("data", workerName);
//        return "workerList";
//    }

    @RequestMapping("searchWorkerForPage")
    public String searchWorkerForPage(String workerName,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      Model model) {
        PageInfo<Worker> workerPageInfo = workerService.searchWorkerForPage(pageNum, workerName);
        // 获取当前页的工人列表
        List<Worker> workerList = workerPageInfo.getList();
        model.addAttribute("workerList", workerList);
        model.addAttribute("pageInfo", workerPageInfo);

        model.addAttribute("pageData", "searchData");
        model.addAttribute("data", workerName);
        return "workerList";
    }

    @RequestMapping("/preSaveWorker")
    public String preSaveWorker() {
        return "workerSave";
    }

    @RequestMapping(value = "/saveWorker", method = RequestMethod.POST)
    public String saveWorker(Worker worker, MultipartFile uploadFile) throws IOException {
        // 获取上传的文件名
        String originalFilename = uploadFile.getOriginalFilename();

        if (StrUtil.isNotEmpty(originalFilename)) {
            int suffixIndex = originalFilename.lastIndexOf('.');
            // 获取后缀
            String suffix = originalFilename.substring(suffixIndex);
            // 当前时间的毫秒值作为前缀
            long prefix = System.currentTimeMillis();
            // 文件名
            String fullFileName = prefix + suffix;

            // 拼接路径
            File parentPath = new File(location);
            // 条件成立：服务器路径不存在创建路径
            if (!parentPath.exists()) {
                parentPath.mkdirs();
            }
            // 全路径
            File fullPath = new File(parentPath, fullFileName);
            // 上传
            uploadFile.transferTo(fullPath);
            worker.setWorkerImage(fullFileName);
        }
        workerService.saveWorker(worker);
        return "redirect:/worker/listWorkerForPage";
    }

    @RequestMapping("/deleteWorker")
    @ResponseBody
    public String deleteCustomer(@RequestParam("wid") Integer wid) {
        int rows = workerService.deleteWorkerById(wid);
        if (rows > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/preUpdateWorker/{wid}")
    public String preUpdateWorker(@PathVariable("wid") Integer wid, Model model) {
        Worker worker = workerService.getWorkerById(wid);
        model.addAttribute("worker", worker);
        return "workerUpdate";
    }

    @RequestMapping(value = "/updateWorker", method = RequestMethod.POST)
    public String updateWorker(Worker worker, MultipartFile uploadFile) throws IOException {
        // 获取上传的文件名
        String originalFilename = uploadFile.getOriginalFilename();

        if (StrUtil.isNotEmpty(originalFilename)) {
            int suffixIndex = originalFilename.lastIndexOf('.');
            // 获取后缀
            String suffix = originalFilename.substring(suffixIndex);
            // 当前时间的毫秒值作为前缀
            long prefix = System.currentTimeMillis();
            // 文件名
            String fullFileName = prefix + suffix;

            // 拼接路径
            File parentPath = new File(location);
            // 条件成立：服务器路径不存在创建路径
            if (!parentPath.exists()) {
                parentPath.mkdirs();
            }
            // 全路径
            File fullPath = new File(parentPath, fullFileName);
            // 上传
            uploadFile.transferTo(fullPath);
            worker.setWorkerImage(fullFileName);
        }
        workerService.updateWorker(worker);
        return "redirect:/worker/listWorkerForPage";
    }

    @RequestMapping("/adjustSalary")
    @ResponseBody
    public String adjustSalary(@RequestParam("wid") Integer wid, @RequestParam("workerSalary") Integer workerSalary) {
        int rows = workerService.adjustSalary(wid, workerSalary);
        if (rows > 0) {
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("/batchDeleteWorker")
    @ResponseBody
    public String batchDeleteWorker(@RequestParam("ids") String ids) {
        try {
            int rows = workerService.batchDeleteWorker(ids);
            if (rows > 0) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            log.error("出现异常，回滚事务："+ e);
            return "fail";
        }
    }

    @RequestMapping("/countWorkerNoSendWater")
    public String countWorkerNoSendWater(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageInfo<Worker> workerPageInfo = workerService.countWorkerNoSendWater(pageNum);
        List<Worker> workerList = workerPageInfo.getList();

        model.addAttribute("workerList", workerList);
        model.addAttribute("pageInfo", workerPageInfo);
        model.addAttribute("pageData", "countWorkerNoSendWater");

        return "workerList";
    }

}
