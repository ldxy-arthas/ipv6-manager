package manager.domain.ipv6.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@Entity
@Document(collation = "t_ipv6_info")
public class TIpv6Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private String id;
    private String regionId;
    private String ipv6;
    private String is_used;
    private Date createTime;
    private Date update_time;
}
