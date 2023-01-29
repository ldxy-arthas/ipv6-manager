package manager.infrastructure.dao;

import manager.domain.system.model.entity.TCollectionLog;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *@author yuluo
 *@createTime 2023-01-29  19:01
 */

public interface CollectionDAO extends MongoRepository<TCollectionLog, String> {
}
