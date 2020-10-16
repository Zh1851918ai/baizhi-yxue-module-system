package com.baizhi.Controller.HouDaunController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//注解主要用于action接口上
@Controller
@RequestMapping("/exit")
public class ExitController {

    @RequestMapping("/ead")
    public String exit(HttpServletRequest request){
        HttpSession session = request.getSession();
        //删除之前存储的session对象
        session.removeAttribute("admin");
        //手动销毁
        session.invalidate();
        return "login";
    }
}
