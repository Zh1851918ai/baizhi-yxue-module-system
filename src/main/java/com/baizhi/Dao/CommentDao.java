package com.baizhi.Dao;

import com.baizhi.Entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentDao {

    List<Comment> queryAllOne();

    List<Comment>QueryAllTwo(String interact_id);
}
