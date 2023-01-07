package manager.infrastructure.dao;

import manager.domain.system.model.entity.TUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<TUser, String> {
}
