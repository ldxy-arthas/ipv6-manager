package manager.infrastructure.dao;

import manager.domain.ipv6.model.entity.TRegion;
import manager.domain.ipv6.model.entity.TWebsiteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegionDao extends MongoRepository<TRegion, String> {
    public TRegion findByRegionName(String regionName);
}
