package com.baizhi.app;

import com.baizhi.Dto.AdminDto;
import com.baizhi.Entity.Admin;
import com.baizhi.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app")
public class AppAdminController {

    @Autowired
    private AdminService adminService;

    @Autowired(required = false)
    private HttpServletRequest request;

    @RequestMapping("login")
    public Map<String, Object> QueryByReleaseAdmin (String username,String password) throws Exception{

        Admin admin = adminService.selectBy(username, password);

        System.out.println(admin);

        //自己封装结果
        List<AdminDto> AdminDTOS = new ArrayList<>();

        HttpSession session = request.getSession();

        AdminDto adminDto = new AdminDto(
            admin.getId(),
                "121231231",
            admin.getUsername()
        );

        AdminDTOS.add(adminDto);

        HashMap<String, Object> map = new HashMap<>();

        if(admin.getUsername().equals(username)&admin.getPassword().equals(password)){
            map.put("data",admin);
            map.put("message", "登录成功!");
            map.put("status", 100);
        }else {
            map.put("status", 101);
            map.put("message", "错误信息!");
        }
        return map;
    }

}
