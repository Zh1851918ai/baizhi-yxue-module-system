package com.baizhi.Controller.HouDaunController;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.Entity.Feedback;
import com.baizhi.Entity.User;
import com.baizhi.Service.FeedbackService;
import com.baizhi.Service.UserService;
import com.baizhi.common.EasyPoiUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("excl")
public class ExclController {
    @Autowired(required = false)
    private HttpServletResponse response;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @RequestMapping("userExport")
    @ResponseBody
    public void UserExport() throws Exception {
        System.out.println("======请求导出报表=========");

        //获取数据集合
        List<User> users = userService.QueryAll();

        for (User user : users) {
            user.setHeadShow("D:\\HouQiXiangMu\\baizhi-yxue-module-system\\src\\main\\webapp\\upload_file\\img\\"+user.getHeadShow());
        }
        System.out.println("UserExport=========="+users);

        EasyPoiUtil.exportExcel(users,"用户信息","用户",User.class,"用户.xls",response);

    }


    @RequestMapping("userImport")
    @ResponseBody
    public void UserImportUtil(MultipartFile multipartFile) throws Exception {
        System.out.println("======请求导如入报表=========");

        //获取数据集合
        List<User> users=EasyPoiUtil.importExcel(multipartFile,1,1,User.class);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @RequestMapping("export")
    @ResponseBody
    public void export() throws Exception {
        System.out.println("======请求导出报表=========");

        //获取数据集合
        List<Feedback> feedbackList=feedbackService.QueryAll();
        System.out.println("Excel=========="+feedbackList);

       EasyPoiUtil.exportExcel(feedbackList,"评论信息","评论",Feedback.class,"评论.xls",response);

    }

    @RequestMapping("import")
    @ResponseBody
    public void importUtil(MultipartFile multipartFile) throws Exception {
        System.out.println("======请求导如入报表=========");

        //获取数据集合
        List<Feedback> feedbackList=EasyPoiUtil.importExcel(multipartFile,1,1,Feedback.class);

        for (Feedback feedback : feedbackList) {
            System.out.println(feedback);
        }

    }
}
