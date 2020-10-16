package com.baizhi.Service;

import com.baizhi.Entity.Admin;

public interface AdminService {
    //登录
    Admin selectBy(String username,String password);
    //注册
    void add (Admin admin);
}
