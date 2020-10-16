package com.baizhi.Entity;

import java.util.Date;

public class Group {
    private String id;
    private String names;
    private int video_num;
    private Date createTime;
    private String user_id;

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", names='" + names + '\'' +
                ", video_num=" + video_num +
                ", createTime=" + createTime +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getVideo_num() {
        return video_num;
    }

    public void setVideo_num(int video_num) {
        this.video_num = video_num;
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

    public Group() {
    }

    public Group(String id, String names, int video_num, Date createTime, String user_id) {
        this.id = id;
        this.names = names;
        this.video_num = video_num;
        this.createTime = createTime;
        this.user_id = user_id;
    }
}
