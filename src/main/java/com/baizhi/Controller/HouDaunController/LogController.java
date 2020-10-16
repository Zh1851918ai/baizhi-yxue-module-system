package com.baizhi.Controller.HouDaunController;


import com.baizhi.Entity.Category;
import com.baizhi.Entity.User;
import com.baizhi.Entity.Video;
import com.baizhi.Log.dao.LogMapper;
import com.baizhi.Log.entity.Log;
import com.baizhi.Log.service.LogService;
import com.baizhi.Service.CategoryService;
import com.baizhi.Service.UserService;
import com.baizhi.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoj
 * @since 2020-09-28
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService service;
    @Autowired
    private LogMapper logMapper;

    @ResponseBody
    @RequestMapping("queryAll")
    public Result queryAll(Integer page, //页码
                           Integer rows, //条数
                           String sidx , String sord,
                           String _search,
                           String searchOper,//表示发送的方式使用的是什么 = ！= 包含 不包含
                           String searchField,//表示需要查找的字段是哪个？ name? id?
                           String searchString //模糊搜索的东西是什么
                            ){
//        List<Log> list = service.list();
        //new 一个结果集
        Result result = new Result();
        if(_search.equals("true")){
            QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
            //创建需要的页码和条数
            IPage<Log> iPage = new Page<>(page,rows);
            //这里的searchField需要用驼峰命名法修改
            queryWrapper.like(searchField,searchString);
            //查询的东西是
            IPage<Log> list = service.page(iPage, queryWrapper);
            //获取结果
            List<Log> categoryList = list.getRecords();
            //获取页码
            Long pages1 = list.getPages();
            //获取总条数
            Integer count= logMapper.selectCount(queryWrapper);
            //返回前端的条数
            result.setRows(categoryList);
            result.setPage(page);// 当前页
            result.setTotal(pages1.intValue());// 总页数
            result.setRecords(count);// 信息总条数
        }else {
            //查询的结果
            QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
            //创建需要的页码和条数
            IPage<Log> iPage = new Page<>(page,rows);
            //查询的东西是
            IPage<Log> list = service.page(iPage, queryWrapper);
            //获取结果
            List<Log> categoryList = list.getRecords();
            //获取页码
            Long pages1 = list.getPages();
            //获取总条数
            Integer count= logMapper.selectCount(queryWrapper);
            //返回前端的条数
            result.setRows(categoryList);
            result.setPage(page);// 当前页
            result.setTotal(pages1.intValue());// 总页数
            result.setRecords(count);// 信息总条数
        }
        return result;
    }

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    /*@Autowired
    private VideoService videoService;*/

    @ResponseBody
    @RequestMapping("recover")
    public void recover(String id){
        String[] cover = id.split(",");
        String newId = cover[0];
        String tabelName = cover[1];
        String operationMethod = cover[2];
        if(operationMethod.contains("删除")){
            if(tabelName.equals("yx_user")){
                System.out.println("===========================================================");
                System.out.println("日志的id是:"+newId);
                System.out.println("操作的表名是:"+tabelName);
                System.out.println("===========================================================");
                Log log = service.getById(newId);
                System.out.println("整个日志信息为"+log);
                System.out.println("===========================================================");
                System.out.println("需要恢复的数据是:"+log.getDataInfo());
                System.out.println("===========================================================");
                String dataInfo = log.getDataInfo();
                User user = new Gson().fromJson(dataInfo, User.class);
                System.out.println(user);
                System.out.println("===========================================================");
                userService.save(user);
                System.out.println("===========================我保存成功了!=================================");
                service.removeById(log.getId());
                System.out.println("===========================================================");
                System.out.println("===========================我恢复成功了，日志也删除了！=================================");
                System.out.println("===========================================================");
            }
            if (tabelName.equals("yx_category")){
                System.out.println("===========================================================");
                System.out.println("日志的id是:"+newId);
                System.out.println("操作的表名是:"+tabelName);
                System.out.println("===========================================================");
                Log log = service.getById(newId);
                System.out.println("整个日志信息为"+log);
                System.out.println("===========================================================");
                System.out.println("需要恢复的数据是:"+log.getDataInfo());
                System.out.println("===========================================================");
                String dataInfo = log.getDataInfo();
                Category category = new Gson().fromJson(dataInfo, Category.class);
                System.out.println(category);
                System.out.println("===========================================================");
                categoryService.save(category);
                System.out.println("===========================我保存成功了!=================================");
                service.removeById(log.getId());
                System.out.println("===========================================================");
                System.out.println("===========================我恢复成功了，日志也删除了！=================================");
                System.out.println("===========================================================");
            }
            if (tabelName.equals("yx_video")){
                System.out.println("===========================================================");
                System.out.println("日志的id是:"+newId);
                System.out.println("操作的表名是:"+tabelName);
                System.out.println("===========================================================");
                Log log = service.getById(newId);
                System.out.println("整个日志信息为"+log);
                System.out.println("===========================================================");
                System.out.println("需要恢复的数据是:"+log.getDataInfo());
                System.out.println("===========================================================");
                String dataInfo = log.getDataInfo();
                /*Video video = new Gson().fromJson(dataInfo, Video.class);
                System.out.println(video);
                System.out.println("===========================================================");
                videoService.save(video);
                System.out.println("===========================我保存成功了!=================================");*/
                service.removeById(log.getId());
                System.out.println("===========================================================");
                System.out.println("===========================我恢复成功了，日志也删除了！=================================");
                System.out.println("===========================================================");
            }
        }
        if(operationMethod.contains("修改")){
            if(tabelName.equals("yx_user")){
                System.out.println("===========================================================");
                System.out.println("日志的id是:"+newId);
                System.out.println("操作的表名是:"+tabelName);
                System.out.println("===========================================================");
                Log log = service.getById(newId);
                System.out.println("整个日志信息为"+log);
                System.out.println("===========================================================");
                System.out.println("需要修改的数据是:"+log.getDataInfo());
                System.out.println("===========================================================");
                String dataInfo = log.getDataInfo();
                User user = new Gson().fromJson(dataInfo, User.class);
                System.out.println(user);
                System.out.println("===========================================================");
                userService.updateById(user);
                System.out.println("===========================我保存成功了!=================================");
                service.removeById(log.getId());
                System.out.println("===========================================================");
                System.out.println("===========================我恢复成功了，日志也删除了！=================================");
                System.out.println("===========================================================");
            }
            if (tabelName.equals("yx_category")){
                System.out.println("===========================================================");
                System.out.println("日志的id是:"+newId);
                System.out.println("操作的表名是:"+tabelName);
                System.out.println("===========================================================");
                Log log = service.getById(newId);
                System.out.println("整个日志信息为"+log);
                System.out.println("===========================================================");
                System.out.println("需要修改的数据是:"+log.getDataInfo());
                System.out.println("===========================================================");
                String dataInfo = log.getDataInfo();
                Category category = new Gson().fromJson(dataInfo, Category.class);
                System.out.println(category);
                System.out.println("===========================================================");
                categoryService.updateById(category);
                System.out.println("===========================我保存成功了!=================================");
                service.removeById(log.getId());
                System.out.println("===========================================================");
                System.out.println("===========================我恢复成功了，日志也删除了！=================================");
                System.out.println("===========================================================");
            }
            if (tabelName.equals("yx_video")){
                System.out.println("===========================================================");
                System.out.println("日志的id是:"+newId);
                System.out.println("操作的表名是:"+tabelName);
                System.out.println("===========================================================");
                Log log = service.getById(newId);
                System.out.println("整个日志信息为"+log);
                System.out.println("===========================================================");
                System.out.println("需要修改的数据是:"+log.getDataInfo());
                System.out.println("===========================================================");
                String dataInfo = log.getDataInfo();
                /*Video video = new Gson().fromJson(dataInfo, Video.class);
                System.out.println(video);
                System.out.println("===========================================================");
                videoService.updateById(video);
                System.out.println("===========================我保存成功了!=================================");
                service.removeById(log.getId());
                System.out.println("===========================================================");
                System.out.println("===========================我恢复成功了，日志也删除了！=================================");
                System.out.println("===========================================================");*/
            }
        }
    }

}

