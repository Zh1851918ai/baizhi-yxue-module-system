package com.baizhi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppCategorySeconde implements Serializable {
            private String id ;  //": "2c4a179d87684c669abd0e2fb1e66dc4",
            private String videoTitle ;  //": "抖音视频",
            private String cover ;  //": "http://q40vnlbog.bkt.clouddn.com/1578650216541_抖音视频.jpg",
            private String path ;  //": "http://q3th1ypw9.bkt.clouddn.com/1578650216541_抖音视频.mp4",
            private Date uploadTime ;  //": "2020-01-23",
            private String description ;  //": "抖音视频",
            private Integer likeCount ;  //": 0,
            private String cateName ;  //": "Java",
            private String categoryId ;  //": "16",
            private String userId ;  //": "1",
            private String userName ;  //": "xiaohei"
}
