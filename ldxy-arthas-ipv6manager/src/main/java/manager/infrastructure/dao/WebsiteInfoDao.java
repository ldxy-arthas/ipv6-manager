package manager.infrastructure.dao;

import manager.domain.ipv6.model.entity.TWebsiteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebsiteInfoDao extends MongoRepository<TWebsiteInfo, String> {
}
