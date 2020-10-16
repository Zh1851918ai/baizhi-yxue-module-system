package com.baizhi.Controller.HouDaunController;

import com.baizhi.Entity.Feedback;
import com.baizhi.Entity.User;
import com.baizhi.Entity.pagess;
import com.baizhi.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//注解主要用于action接口上
@Controller
@RequestMapping("/feedback") //修饰类 相当于struts中的 namespace
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/page")
    @ResponseBody
    public pagess findEmpPage(Integer page, Integer rows, String sidx , String sord) {
        List<Feedback> emps = feedbackService.queryForList(page, rows);
        Integer records = feedbackService.count();
        Integer total = null;
        if (records % rows == 0) {
            total = records / rows;
        } else {
            total = records / rows + 1;
        }

        pagess pages = new pagess();
        pages.setRows(emps);
        pages.setPage(page); // 当前页
        pages.setTotal(total);// 总页数
        pages.setRecords(records); // 信息总条数
        return pages;
    }
   /* @RequestMapping("/edit")
    @ResponseBody
    public com.baizhi.common.Result edit(Feedback feedback, String oper){
        String uuid=null;
        if ("add".equals(oper)) {
            userService.add(feedback);
            System.out.println("Action==================================="+user);
        }
        if ("edit".equals(oper)) {
            userService.update(feedback);
        }
        if ("del".equals(oper)) {
            userService.delete(feedback.getId());
        }
        return com.baizhi.common.Result.ok(feedback);
    }*/
}
