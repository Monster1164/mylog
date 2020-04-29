package com.cb.myblog.controller.admin;



import com.cb.myblog.pojo.User;
import com.cb.myblog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.jar.Attributes;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserServiceImpl userService = null;

    /**
     * 返回登录页面
     * @return
     */
    @GetMapping
    public String loginPage(){

        return "admin/login";
    }

    /**
     * 验证登录密码账号
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username ,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        User user = userService.checkUser(username, password);
        System.out.println("user");
        if(user!=null){
            user.setPaaaword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码错误");
//            attributes.addFlashAttribute("message","111111111");
            return "redirect:/admin";
        }


    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }





}
