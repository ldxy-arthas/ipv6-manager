package manager.domain.ipv6.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection  = "t_ipv6_info")
public class TIpv6Info implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @MongoId
    private String  id;

    /**
     * 地区id
     */
    private String regionId;

    /**
     * ipv6
     */
    private String ipv6;

    /**
     * ipv6是否被使用，是为1，否为0
     */
    private String isUsed;

    @CreatedDate
    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    private Date createTime;

    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    @LastModifiedDate
    private Date updateTime;
}
