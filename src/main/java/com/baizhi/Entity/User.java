package com.baizhi.Entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_user")
public class User implements Serializable {
    @Excel(name = "用户编号")
    private String id;//用户编号
    @Excel(name = "账号名称")
    private String username;//账号名称
    @Excel(name = "手机号")
    private String mobile;//手机号
    @Excel(name = "签名")
    private String sign;//签名
    @Excel(name = "头像",type =2,width =20,height = 20,imageType = 1,savePath = "D:\\HouQiXiangMu\\baizhi-yxue-module-system\\src\\main\\webapp\\upload_file\\img\\")
    @TableField(value = "head_show")
    private String headShow;//头像
    @Excel(name = "账户状态")
    private String status;//账户状态
    @Excel(name = "注册时间")
    @TableField(value = "reg_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regTime;//注册时间
    @Excel(name = "学分")
    private Double score;//学分
    @Excel(name = "绑定微信号")
    private String wechat;//绑定微信号

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sign='" + sign + '\'' +
                ", headShow='" + headShow + '\'' +
                ", status='" + status + '\'' +
                ", regTime=" + regTime +
                ", score=" + score +
                ", wechat='" + wechat + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getHeadShow() {
        return headShow;
    }

    public void setHeadShow(String headShow) {
        this.headShow = headShow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public User() {
    }

    public User(String id, String username, String mobile, String sign, String headShow, String status, Date regTime, Double score, String wechat) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.sign = sign;
        this.headShow = headShow;
        this.status = status;
        this.regTime = regTime;
        this.score = score;
        this.wechat = wechat;
    }
}