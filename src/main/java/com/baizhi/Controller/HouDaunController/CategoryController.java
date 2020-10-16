package com.baizhi.Controller.HouDaunController;

import com.baizhi.Entity.Category;
import com.baizhi.Service.Impl.CategoryServiceImpl;
import com.baizhi.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cate")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @RequestMapping("queryOne")
    public Result<?> queryOne(Integer page, Integer rows, String searchField, String searchString, String searchOper){
        QueryWrapper<Category> queryWrapper=new QueryWrapper();
        queryWrapper.eq("level","1");

        if(searchField!=null){
            if(searchOper.equals("eq"))queryWrapper.eq(searchField,searchString);
            if(searchOper.equals("cn"))queryWrapper.like(searchField,searchString);
        }

        IPage<Category> iPage=new Page<>(page,rows);
        IPage<Category> pages = categoryService.page(iPage, queryWrapper);
        List<Category> list=pages.getRecords();
        int records=(int)pages.getTotal();
        return Result.ok(list,page,pages.getPages(),records);
    }
    @RequestMapping("queryTwo")
    public Result<?> queryTwo(Integer page,Integer rows,String id,String searchField,String searchString,String searchOper){

        QueryWrapper<Category> queryWrapper=new QueryWrapper();
        queryWrapper.eq("p_id",id);

        if(searchField!=null){
            if(searchOper.equals("eq"))queryWrapper.eq(searchField,searchString);
            if(searchOper.equals("cn"))queryWrapper.like(searchField,searchString);
        }

        IPage<Category> iPage=new Page<>(page,rows);
        IPage<Category> pages = categoryService.page(iPage, queryWrapper);
        List<Category> list=pages.getRecords();

        int records=(int)pages.getTotal();
        return Result.ok(list,page,pages.getPages(),records);
    }
    @RequestMapping("/edit")
    public Result<?> edit(Category category,String oper,String sid){
        if("add".equals(oper)){
            if(!"".equals(sid)&&sid!=null) {
                category.setLevel("2");
                category.setId(null);
                category.setP_id(sid);
            }else {
                category.setLevel("1");
            }
            categoryService.save(category);
        }
        if("edit".equals(oper)){
            categoryService.updateById(category);
        }
        if("del".equals(oper)){
            Category category1=categoryService.getById(category.getId());
            if (category1.getLevel()=="2") {
                categoryService.removeById(category1.getId());
                return Result.ok();
            }
            QueryWrapper<Category> queryWrapper = new QueryWrapper<>();//查询构造器
            queryWrapper.eq("p_id",category.getId());
            List<Category> list = categoryService.list(queryWrapper);
            if(list.size()==0)categoryService.removeById(category.getId());
        }
        return Result.ok();
    }
}
