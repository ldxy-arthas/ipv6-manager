package manager.domain.ipv6.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Data
@Entity
public class TRegionLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")

    private String id;
    private Date createTime;
    private Date updateTime;
    private String regionLevelName;

}
