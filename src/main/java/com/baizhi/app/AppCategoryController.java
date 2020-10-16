package com.baizhi.app;

import com.baizhi.Dto.AppCategory;
import com.baizhi.Dto.AppCategorySeconde;
import com.baizhi.Dto.VideoDto;
import com.baizhi.Entity.Category;
import com.baizhi.Entity.Video;
import com.baizhi.Service.CategoryService;
import com.baizhi.Service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app")
public class AppCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VideoService videoService;

    @GetMapping("queryAllCategory")
    public Map<String, Object> queryAllCategory() {
        System.out.println("我进来了");
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("level", "1");
        //查询所有的一级类别
        List<Category> list = categoryService.list(categoryQueryWrapper);
        if (list == null) {
            map.put("data", null);
            map.put("message", "查询失败");
            map.put("status", "104");
            return map;
        }

        List<AppCategory> categoryList = new ArrayList<>();
        for (Category category : list) {
            AppCategory appCategory = new AppCategory();
            QueryWrapper<Category> categoryQueryWrapper1 = new QueryWrapper<>();
            categoryQueryWrapper1.eq("level", "2");
            categoryQueryWrapper1.eq("p_id", category.getId());
            //查询所有二级类别
            List<Category> list1 = categoryService.list(categoryQueryWrapper1);

            System.out.println(category.getLevel() + "level");
            appCategory.setId(category.getId());
            appCategory.setCateName(category.getName());
            appCategory.setLevels(category.getLevel());
            appCategory.setParentId(category.getP_id());

            List<AppCategory> categoryList1 = new ArrayList<>();
            for (Category category1 : list1) {
                AppCategory appCategory1 = new AppCategory();
                appCategory1.setId(category1.getId());
                appCategory1.setCateName(category1.getName());
                appCategory1.setLevels(category1.getLevel());
                appCategory1.setParentId(category1.getP_id());
                appCategory1.setCategoryList(null);
                categoryList1.add(appCategory1);
            }
            appCategory.setCategoryList(categoryList1);
            categoryList.add(appCategory);
            System.out.println();
        }
        if (categoryList != null) {
            map.put("data", categoryList);
            map.put("message", "查询成功");
            map.put("status", "100");
        }
        return map;
    }

    /**
     *
     * @param cateId 二级类别ID
     * @return
     */
   @GetMapping("queryCateVideoList")
    public Map<String,Object> queryCateVideoList(String cateId){
        System.out.println("进来了呢");
        Map<String, Object> result = new HashMap<>();
        List<Video> list = videoService.queryThree(cateId);
       List<AppCategorySeconde> appcategoryseconde = new ArrayList<>();
       if (list != null) {
            for (Video video : list) {
                AppCategorySeconde appCategorySeconde = new AppCategorySeconde();
                appCategorySeconde.setId(video.getId());
                appCategorySeconde.setVideoTitle(video.getTitle());
                appCategorySeconde.setCover("http://localhost:8081/yingx/upload_file/img/"+video.getCoverUrl());
                appCategorySeconde.setPath(video.getVideoUrl());
                appCategorySeconde.setUploadTime(video.getCreateTime());
                appCategorySeconde.setDescription(video.getIntro());
                appCategorySeconde.setLikeCount(8);
                appCategorySeconde.setCateName(video.getCategory().getName());
                appCategorySeconde.setCategoryId(video.getC_id());
                appCategorySeconde.setUserId(video.getUser_id());
                appCategorySeconde.setUserName(video.getUser().getUsername());
                appcategoryseconde.add(appCategorySeconde);
            }

       }

       if(appcategoryseconde!=null){
           result.put("data", appcategoryseconde);
           result.put("message", "查询成功!");
           result.put("status", 100);
       }else{
           result.put("message", "查询失败!");
           result.put("status", 104);
       }

       return result;
    }
}
