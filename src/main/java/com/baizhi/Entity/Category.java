package com.baizhi.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("yx_category")
public class Category {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    private String name;
    private String level;
    private String p_id;


}
