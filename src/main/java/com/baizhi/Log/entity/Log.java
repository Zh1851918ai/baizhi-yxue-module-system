package com.baizhi.Log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("log")
public class Log implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 日志编号
     */
    @TableId(type = IdType.UUID)
    private String id;
    /*
    * 用户名称
    * */
    private String username;
    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date operationAt;
    /**
     * 操作表名
     */
    private String tableName;
    /**
     * 操作的业务类型
     */
    private String operationMethod;
    /**
     * 操作的方法签名
     */
    private String methodName;
    /**
     * 操作数据的ID
     */
    private String dataId;

    /**
     * 如果是删除，记录删除的数据以便于回复
     */
    private String dataInfo;


}
