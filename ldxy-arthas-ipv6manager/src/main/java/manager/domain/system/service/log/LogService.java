package manager.domain.system.service.log;


import manager.domain.system.model.entity.TLog;

/**
 * @Author: yuluo
 * @CreateTime: 2023-1-08 17:49
 * @Description: TODO
 */
public interface LogService {

    /**
     * 保存日志信息T
     * @param operLog
     */
    void saveLog(TLog operLog);

    /**
     * 删除日志文件
     */
    boolean deleteLogFile();
}
