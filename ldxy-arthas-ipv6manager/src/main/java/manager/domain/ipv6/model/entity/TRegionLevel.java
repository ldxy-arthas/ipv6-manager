package manager.domain.ipv6.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
@Data
@Entity
@Document(collation = "t_region_level")

public class TRegionLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private String id;
    private Date createTime;
    private Date updateTime;
    private String regionLevelName;

}
