package manager.application;

import jakarta.annotation.Resource;
import manager.infrastructure.utils.LoggerService;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  14:10
 * @Description: TODO
 */

@Component
public abstract class DefaultServiceManager {

    @Resource
    private LoggerService loggerService;

}
