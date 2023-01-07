package manager.domain.system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Data
@Entity

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
