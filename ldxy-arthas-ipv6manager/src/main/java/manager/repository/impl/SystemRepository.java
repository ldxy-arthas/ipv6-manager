package manager.repository.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import manager.infrastructure.dao.LogDao;
import manager.infrastructure.dao.UserDao;
import manager.repository.DefaultRepository;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class SystemRepository extends DefaultRepository {

    private final UserDao userDao;

    private final LogDao logDao;

}
