package manager.infrastructure.dao;

import manager.domain.system.model.entity.TUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDao extends MongoRepository<TUser, String> {

    Optional<TUser> findByUsername(String username);

}
