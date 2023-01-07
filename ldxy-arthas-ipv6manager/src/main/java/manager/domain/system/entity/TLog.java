package manager.domain.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
@Data
@Entity
@Document(collation = "t_log")
public class TLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private String id;
    private String userId;
    private String userOperation;
    private Date opTime;
    private String hint;

}
