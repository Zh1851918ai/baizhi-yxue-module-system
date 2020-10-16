package com.baizhi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppCategory implements Serializable {

    private String id; //": "1",
    private String cateName; //": "软件开发",
    private String levels; //": 1,
    private String parentId; //": "",
    private List<AppCategory> categoryList; //": [
}
