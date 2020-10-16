package com.baizhi.Entity;

import java.io.Serializable;
import java.util.Date;
public class Comment implements Serializable {

    private String id;
    private String content;
    private Date createTime;
    private String user_id;
    private String video_id;
    private String interact_id;

    private User user;
    private Video video;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", user_id='" + user_id + '\'' +
                ", video_id='" + video_id + '\'' +
                ", interact_id='" + interact_id + '\'' +
                ", user=" + user +
                ", video=" + video +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getInteract_id() {
        return interact_id;
    }

    public void setInteract_id(String interact_id) {
        this.interact_id = interact_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Comment(String id, String content, Date createTime, String user_id, String video_id, String interact_id, User user, Video video) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.user_id = user_id;
        this.video_id = video_id;
        this.interact_id = interact_id;
        this.user = user;
        this.video = video;
    }
}
