package manager.domain.system.service.log.impl;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.vo.LogOperationResponseVO;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.repository.impl.SystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: yuluo
 * @CreateTime: 2022-10-01  17:52
 * @Description: TODO
 */

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    private final SystemRepository system;

    /**
     * 对日志进行持久化操作
     *
     * @param operaLog 日志对象
     */
    public void saveLog(TLog operaLog) {

        String businessTypeDes;

        switch (operaLog.getBusinessType()) {
            case 0: {
                businessTypeDes = BusinessType.OTHER.getDes();
                break;
            }
            case 1: {
                businessTypeDes = BusinessType.INSERT.getDes();
                break;
            }
            case 2: {
                businessTypeDes = BusinessType.SELECT.getDes();
                break;
            }
            case 3: {
                businessTypeDes = BusinessType.UPDATE.getDes();
                break;
            }
            case 4: {
                businessTypeDes = BusinessType.DELETE.getDes();
                break;
            }
            case 5: {
                businessTypeDes = BusinessType.ASSIGNIPv6.getDes();
                break;
            }
            case 6: {
                businessTypeDes = BusinessType.LOGIN.getDes();
                break;
            }
            default: {
                businessTypeDes = "处理失败！";
            }
        }
        operaLog.setBusinessTypeDes(businessTypeDes);

        // 保存用户日志操作到数据库中
        this.system.getLogDao().save(operaLog);

    }

    /**
     * 删除数据库用户操作日志
     */
    public LogOperationResponseVO deleteDBLog() {

        this.system.getLogDao().deleteAll();

        return LogOperationResponseVO.builder()
                .flag(true)
                .build();
    }
}
