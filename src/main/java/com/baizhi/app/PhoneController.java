package com.baizhi.app;

import com.baizhi.common.SecurityCode;
import com.baizhi.common.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("app")
public class PhoneController {

    @Autowired(required = false)
    private HttpServletRequest request;


    @RequestMapping("getPhoneCode")
    public HashMap<String, Object> getPhoneCodes(String phone){
        //获取验证码随机数
        String code = SecurityCode.getSecurityCode();
        HttpSession session = request.getSession();
        session.setAttribute("code",code);

        System.out.println("Code==========="+code);

        //发送验证码到用户手机
        String responseData = SendSmsUtil.sendPhoneCode(phone, code);
        System.out.println("responseData=============="+responseData);

        HashMap<String, Object> map = new HashMap<>();

        if(responseData!=null){
            map.put("data",phone);
            map.put("message","验证码发送成功");
            map.put("status","100");
        }else{
            map.put("data",null);
            map.put("message","验证码发送失败");
            map.put("status","104");
        }
        return map;
    }
}
