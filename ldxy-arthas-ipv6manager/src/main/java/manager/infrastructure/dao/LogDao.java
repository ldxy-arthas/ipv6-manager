package manager.infrastructure.dao;

import manager.domain.system.model.entity.TLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogDao extends MongoRepository<TLog, String> {
}
