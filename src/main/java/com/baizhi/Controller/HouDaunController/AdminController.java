package com.baizhi.Controller.HouDaunController;

import com.baizhi.Entity.Admin;
import com.baizhi.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//注解主要用于action接口上
@Controller
@RequestMapping("/admin") //修饰类 相当于struts中的 namespace
public class AdminController {
    @Autowired
    private AdminService adminService;

    /*@Autowired
    private RedisTemplate redisTemplate;
*/
    @RequestMapping("/login") //修饰类 相当于struts中的 namespace
    public String QueryBy(HttpServletRequest request,String username,String password ){
        Admin admin = adminService.selectBy(username,password);
        HttpSession session = request.getSession();
        if(admin!=null){
            session.setAttribute("admin",admin);
            return "HouDuan/main";
        }else{
            String message="用户名不存在";
            request.setAttribute("message",message);
            return "login";
        }
    }

    /*public String add(Admin admin){
        adminService.add(admin);
        return "regist";
    }*/

    /*
    * 发送手机验证码
    *
    * *//*
    @RequestMapping("/phoneCode")
    @ResponseBody
    public Result<?> sendPhoneCode( HttpServletRequest request,String phone){
        //获取验证码随机数
        String code = SecurityCode.getSecurityCode();

        ValueOperations ops = redisTemplate.opsForValue();
        //将验证码随机数保存到session或Redis中
        ops.set(phone,code,1, TimeUnit.MINUTES);
        //发送验证码到用户手机
        String responseData = SendSmsUtil.sendPhoneCode(phone, code);
        return Result.ok(responseData);
    }*/
}
