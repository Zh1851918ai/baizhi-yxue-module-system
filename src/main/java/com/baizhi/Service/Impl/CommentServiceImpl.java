package com.baizhi.Service.Impl;

import com.baizhi.Dao.CommentDao;
import com.baizhi.Entity.Comment;
import com.baizhi.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@Service 注解主要用于service类上
@Service
//用在类上，会将事务控制的配置应用于当前类里所有方法
@Transactional(propagation = Propagation.SUPPORTS)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> queryAllOne() {
        List<Comment> commentOne = commentDao.queryAllOne();
        return commentOne;
    }

    @Override
    public List<Comment> QueryAllTwo(String interact_id) {
        List<Comment> commentTwo = commentDao.QueryAllTwo(interact_id);
        return commentTwo;
    }
}
