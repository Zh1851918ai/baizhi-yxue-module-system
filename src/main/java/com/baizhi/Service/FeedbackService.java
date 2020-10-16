package com.baizhi.Service;

import com.baizhi.Entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> queryForList(Integer page, Integer rows);

    Integer count();

    List<Feedback> QueryAll();

}
