package com.baizhi.Dao;

import com.baizhi.Entity.Video;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoDao {
    List<Video> QueryAll();

    void delete(String id);

    void add(Video video);

    void update(Video video);

    void updateCv(Video video);

    /*void updatev(Video video);*/



    //前端  模糊查询
    List<Video> queryBy(String content);

    //详情
    Video queryOne(String videoId);

    List<Video> queryTwo();

    List<Video> queryThree(String cateId);

    //测试ES
    List<Video>query();

    //在添加视频中上传到Es
    Video QueryEs(String id);

}
