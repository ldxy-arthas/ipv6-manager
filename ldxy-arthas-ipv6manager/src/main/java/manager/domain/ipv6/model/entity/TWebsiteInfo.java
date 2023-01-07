package manager.domain.ipv6.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@Entity
@Document(collation = "t_website_info")
public class TWebsiteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private String id;
    private String websiteInfoAddress;
    private String ipv6Status;
    private String isIpv6;
    private Date createTime;
    private Date updateTime;

}
