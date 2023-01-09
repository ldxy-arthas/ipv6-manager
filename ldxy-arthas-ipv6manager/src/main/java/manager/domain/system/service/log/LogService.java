package manager.domain.system.service.log;


import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.vo.LogOperationResponseVO;

/**
 * @Author: yuluo
 * @CreateTime: 2023-1-08 17:49
 * @Description: TODO
 */

public interface LogService {

    /**
     * 保存日志信息
     */
    void saveLog(TLog operaLog);

    LogOperationResponseVO deleteDBLog();
}
