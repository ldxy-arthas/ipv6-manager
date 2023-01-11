package manager.infrastructure.utils;

import manager.infrastructure.Enum.ClassNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  13:52
 * @Description: TODO
 */

@Component
public record LoggerService() {

    public static final Logger systemServiceManagerLogger = LoggerFactory.getLogger(ClassNameEnum.SYSTEM_SERVICE_MANAGER.getDes());

    public static final Logger AuthenticationServiceImplLogger = LoggerFactory.getLogger(ClassNameEnum.AUTHENTICATION_SERVICE_IMPL.getDes());

    public static final Logger LogServiceImplLogger = LoggerFactory.getLogger(ClassNameEnum.LOG_SERVICE_IMPL.getDes());

}
