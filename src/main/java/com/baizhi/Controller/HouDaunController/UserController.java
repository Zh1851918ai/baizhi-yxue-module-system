package com.baizhi.Controller.HouDaunController;

import com.baizhi.Entity.pagess;
import com.baizhi.Entity.User;
import com.baizhi.Log.anno.AddCache;
import com.baizhi.Log.anno.DelCache;
import com.baizhi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

//注解主要用于action接口上
@Controller
@RequestMapping("/user") //修饰类 相当于struts中的 namespace
public class UserController {
    @Autowired
    private UserService userService;

    @AddCache
    @RequestMapping("/page")
    @ResponseBody
    public pagess findEmpPage(Integer page, Integer rows, String sidx , String sord) {
        List<User> emps = userService.queryForList(page, rows);
        Integer records = userService.count();
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

    @DelCache
    @RequestMapping("/edit")
    @ResponseBody
    public com.baizhi.common.Result edit(User user, String oper){
        String uuid=null;
        if ("add".equals(oper)) {
            userService.add(user);
            System.out.println("Action==================================="+user);
        }
        if ("edit".equals(oper)) {
            userService.update(user);
        }
        if ("del".equals(oper)) {
            userService.delete(user.getId());
        }
        return com.baizhi.common.Result.ok(user);
    }


     /**
     * @param headShow 用于接收上传文件
     * @param id 接收当前上传的文件属于那条数据的id,用于更新文件保存路径
     * @return
        */
    @DelCache
    @RequestMapping("/headUpload")
    public void headUpload(HttpServletRequest request, MultipartFile headShow, String id) throws Exception {
        System.out.println("id:"+id);
        System.out.println("id:"+id);
        String filename = headShow.getOriginalFilename();
        //System.out.println(filename);
        // 获取图片保存的路径
        String realPath = request.getServletContext().getRealPath("/upload_file/img");
        // 保存上传文件到服务器
        headShow.transferTo(new File(realPath+"/"+filename));

        User user = new User();
        user.setId(id);
        /*user.setHeadShow(request.getContextPath()+"/upload_file/img/"+filename);*/
        user.setHeadShow(filename);
        userService.updateImg(user);
    }

}