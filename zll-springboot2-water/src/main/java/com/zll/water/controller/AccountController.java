package com.zll.water.controller;

import com.zll.water.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * login是post请求
     * @param userName
     * @param userPwd
     * @param model model 以键值对形式让后端数据传入前端
     * @return
     */
    @RequestMapping("/login")
    public String login(String userName, String userPwd, Model model, HttpSession session) {
        boolean loginResult = accountService.login(userName, userPwd);
        if (loginResult) {
            // 在前端显示当前用户
            session.setAttribute("currentUser", userName);
            return "waterMainMenu";
        } else {
            model.addAttribute("loginFail","用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("/preUpdateUserPwd")
    public String preUpdateUserPwd() {
        return "userPwdUpdate";
    }

    @RequestMapping("/updateUserPwd")
    @ResponseBody
    public String updateUserPwd(@RequestParam("userName")String userName, @RequestParam("newUserPwd")String newUserPwd) {
//        if (log.isInfoEnabled()) {
//            log.info(userName);
//        }
        int rows = accountService.updateUserPwd(userName, newUserPwd);
        if (rows > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
