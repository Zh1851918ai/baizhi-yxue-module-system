package com.baizhi.Service;

import com.baizhi.Entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> queryAllOne();
    List<Comment>QueryAllTwo(String interact_id);
}
