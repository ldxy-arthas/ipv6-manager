package manager.infrastructure.dao;

import manager.domain.ipv6.model.entity.TRegion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegionDao extends MongoRepository<TRegion, String> {
}
