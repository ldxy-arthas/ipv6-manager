package manager.domain.system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection  = "t_log")
public class TLog {

    @MongoId
    private String id;

    private String userId;

    private String userOperation;

    @CreatedDate
    @JsonFormat(locale = "zh",  timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
    private Date opTime;

    private String hint;

}
