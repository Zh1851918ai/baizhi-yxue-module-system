package com.baizhi.Dao;

import com.baizhi.Entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminDao {
    //登录
    Admin selectBy(@Param("username") String username, @Param("password")String password);
    //注册
    void add (Admin admin);

}
