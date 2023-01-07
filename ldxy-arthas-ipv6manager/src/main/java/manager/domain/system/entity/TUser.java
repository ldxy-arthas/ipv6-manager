package manager.domain.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@Entity
@Document(collation = "t_user")
public class TUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private String id;
    private String username;
    private String password;
    private String region;
    private Date createTime;
    private Date updateTime;

}
