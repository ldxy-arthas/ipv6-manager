package manager.infrastructure.dao;

import manager.domain.ipv6.model.entity.TIpv6Info;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ipv6InfoDao extends MongoRepository<TIpv6Info, String> {

}
