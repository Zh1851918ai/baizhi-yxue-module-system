package com.baizhi.Entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
@Document(indexName = "yingx",type = "video")
public class Video implements Serializable {
    @Id
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word")
    private String title;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word")
    private String intro;

    @Field(type=FieldType.Keyword)
    private String coverUrl;

    @Field(type=FieldType.Keyword)
    private String videoUrl;

    @Field(type = FieldType.Date)
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @Field(type=FieldType.Keyword)
    private String user_id;

    @Field(type=FieldType.Keyword)
    private String c_id;

    @Field(type=FieldType.Keyword)
    private String  gr_id;

    private User user;

    private Group group;

    private Category category;

    private Play play;

    private YxLike yxLike;


    public YxLike getYxLike() {
        return yxLike;
    }

    public void setYxLike(YxLike yxLike) {
        this.yxLike = yxLike;
    }



    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public Video(String id, String title, String intro, String coverUrl, String videoUrl, Date createTime, String user_id, String c_id, String gr_id, User user, Group group, Category category, Play play) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.coverUrl = coverUrl;
        this.videoUrl = videoUrl;
        this.createTime = createTime;
        this.user_id = user_id;
        this.c_id = c_id;
        this.gr_id = gr_id;
        this.user = user;
        this.group = group;
        this.category = category;
        this.play = play;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", createTime=" + createTime +
                ", user_id='" + user_id + '\'' +
                ", c_id='" + c_id + '\'' +
                ", gr_id='" + gr_id + '\'' +
                ", user=" + user +
                ", group=" + group +
                ", category=" + category +
                ", play=" + play +
                '}';
    }

    public Video() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getGr_id() {
        return gr_id;
    }

    public void setGr_id(String gr_id) {
        this.gr_id = gr_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Video(String id, String title, String intro, String coverUrl, String videoUrl, Date createTime, String user_id, String c_id, String gr_id) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.coverUrl = coverUrl;
        this.videoUrl = videoUrl;
        this.createTime = createTime;
        this.user_id = user_id;
        this.c_id = c_id;
        this.gr_id = gr_id;
    }

    public Video(String id, String title, String intro, String coverUrl, String videoUrl, Date createTime, String user_id, String c_id, String gr_id, User user, Group group, Category category) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.coverUrl = coverUrl;
        this.videoUrl = videoUrl;
        this.createTime = createTime;
        this.user_id = user_id;
        this.c_id = c_id;
        this.gr_id = gr_id;
        this.user = user;
        this.group = group;
        this.category = category;
    }
}
