package com.baizhi.Service.Impl;

import com.baizhi.Dao.CategoryDao;
import com.baizhi.Entity.Category;
import com.baizhi.Service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * ServiceImpl<AdminDao, Admin>
 *     泛型1： 指定你的DAO接口
 *     泛型2： 指定操作的表对应的实体类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
}
