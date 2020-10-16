package com.baizhi.Service.Impl;

import com.baizhi.Dao.FeedbackDao;
import com.baizhi.Entity.Feedback;
import com.baizhi.Entity.User;
import com.baizhi.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@Service 注解主要用于service类上
@Service
//用在类上，会将事务控制的配置应用于当前类里所有方法
@Transactional(propagation = Propagation.SUPPORTS)
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> queryForList(Integer page, Integer rows) {
        // 计算每页第一条和最后一条
        Integer begin = (page-1)*rows+1;
        Integer end = page * rows;
        return feedbackDao.queryForList(begin, end);
    }

    @Override
    public Integer count() {

        return feedbackDao.count();
    }

    @Override
    public List<Feedback> QueryAll() {
        List<Feedback> feedbacks = feedbackDao.QueryAll();
        return feedbacks;
    }


}
