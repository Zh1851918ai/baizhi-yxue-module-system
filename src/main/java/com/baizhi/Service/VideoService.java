package com.baizhi.Service;

import com.baizhi.Entity.Video;

import java.util.List;

public interface VideoService{
    List<Video> QueryAll();

    void delete(String id);

    void add(Video video);

    void update(Video video);

    void updateCv(Video video);

    /*void updatev(Video video);*/


    //前端
    List<Video> queryBy(String content);

    //详情
    Video queryOne(String videoId);

    List<Video> queryTwo();

    List<Video> queryThree(String cateId);

    //Es 测试
    List<Video> querySearchVideo(String content);
    //Es高亮查询
    List<Video> querySearchVideos(String content);

    //在添加视频中上传到Es
    Video QueryEs(String id);
}
