package com.baizhi.Service.Impl;

import com.baizhi.Dao.UserDao;
import com.baizhi.Entity.User;
import com.baizhi.Log.anno.AddCache;
import com.baizhi.Log.anno.YxueLog;
import com.baizhi.Service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

//@Service 注解主要用于service类上
@Service
//用在类上，会将事务控制的配置应用于当前类里所有方法
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl  extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;
    @Resource
    private HttpServletRequest request;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<User> queryForList(Integer page, Integer rows) {
    // 计算每页第一条和最后一条
        Integer begin = (page-1)*rows+1;
        Integer end = page * rows;
        return userDao.queryForList(begin, end);
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public void  add(User user) {
        String uid = UUID.randomUUID().toString();
        user.setId(uid);

        userDao.add(user);
    }

    @Override
    @YxueLog(value = "删除用户",tableName = "yx_user",methodName = "delete")
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void updateImg(User user) {
        userDao.updateImg(user);
    }

    @Override
    public List<User> QueryAll() {

        List<User> users = userDao.QueryAll();
        return users;
    }
}
