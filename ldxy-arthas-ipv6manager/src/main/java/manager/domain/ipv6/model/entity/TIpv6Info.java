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

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection  = "t_ipv6_info")
public class TIpv6Info {

    @MongoId
    private String  id;
    private String regionId;
    private String ipv6;
    private String isUsed;

    @CreatedDate
    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    private Date createTime;

    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    @LastModifiedDate
    private Date updateTime;
}
