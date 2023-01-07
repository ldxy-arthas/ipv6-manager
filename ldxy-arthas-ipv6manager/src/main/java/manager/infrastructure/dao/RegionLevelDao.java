package manager.infrastructure.dao;

import manager.domain.ipv6.model.entity.TRegionLevel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegionLevelDao extends MongoRepository<TRegionLevel, String> {
}
