package com.baizhi.Entity;

import java.util.Date;

public class YxLike {
    private String id;
    private Date createAt;
    private String user_id;
    private String video_id;

    @Override
    public String toString() {
        return "YxLike{" +
                "id='" + id + '\'' +
                ", createAt=" + createAt +
                ", user_id='" + user_id + '\'' +
                ", video_id='" + video_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public YxLike() {
    }

    public YxLike(String id, Date createAt, String user_id, String video_id) {
        this.id = id;
        this.createAt = createAt;
        this.user_id = user_id;
        this.video_id = video_id;
    }
}
