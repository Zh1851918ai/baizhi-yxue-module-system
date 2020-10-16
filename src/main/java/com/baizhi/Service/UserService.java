package com.baizhi.Service;

import com.baizhi.Entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> queryForList(Integer page, Integer rows);

    int count();

    void add(User user);

    void delete(String id);

    void update(User user);

    void updateImg(User user);


    List<User>QueryAll();
}
