package manager.domain.system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_log")
public class TLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @MongoId
    private String id;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除 5分配ip）
     */
    private Integer businessType;

    /**
     * 操作类型
     */
    @Field("business_type_des")
    private String businessTypeDes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 请求url
     */
    private String operUrl;

    /**
     * 操作地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    @CreatedDate
    @JsonFormat(locale = "zh", timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    private Date opTime;

    private String hint;

    @Field("create_time")
    @CreatedDate
    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    private java.util.Date createTime;

    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    @LastModifiedDate
    private java.util.Date updateTime;

}
