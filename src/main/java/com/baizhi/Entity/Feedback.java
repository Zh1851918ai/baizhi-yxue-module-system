package com.baizhi.Entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@ExcelTarget("Feedback")
public class Feedback {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "内容")
    private String content;
    @Excel(name = "时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @Excel(name = "用户编号")
    private String user_id;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Feedback() {
    }

    public Feedback(String id, String title, String content, Date createTime, String user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.user_id = user_id;
    }
}
