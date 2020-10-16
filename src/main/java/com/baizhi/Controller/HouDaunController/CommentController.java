package com.baizhi.Controller.HouDaunController;

import com.baizhi.Entity.Comment;
import com.baizhi.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/queryOne")
    @ResponseBody
    public List<Comment> queryOne(){
        List<Comment> commentOne = commentService.queryAllOne();
        return commentOne;
    }

    @RequestMapping("/queryTwo")
    @ResponseBody
    public List<Comment> queryTwo(String interact_id){
        List<Comment> commentTwo = commentService.QueryAllTwo(interact_id);
        return commentTwo;
    }

}
