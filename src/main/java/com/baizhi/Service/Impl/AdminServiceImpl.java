package com.baizhi.Service.Impl;

import com.baizhi.Dao.AdminDao;
import com.baizhi.Entity.Admin;
import com.baizhi.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//用在类上，会将事务控制的配置应用于当前类里所有方法
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /*
     * 如果类上使用事务注解，同时类里面的某个方法也使用了事务注解
     *   那么最终会议方法上事务注解的配置给方法加事务 相当于覆盖 方法上的事务优先
     * */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Admin selectBy(String username,String password) {
        Admin admin = adminDao.selectBy(username,password);
        return admin;
    }

    @Override
    public void add(Admin admin) {
        adminDao.add(admin);
    }
}
