package manager.domain.system.service.log.impl;

import lombok.extern.slf4j.Slf4j;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Author: yuluo
 * @CreateTime: 2022-10-01  17:52
 * @Description: TODO
 */

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    /**
     * 对日志进行持久化操作
     *
     * @param operLog 日志对象
     */
    public void saveLog(SysLog operLog) {

        String businessTypeDes;

        System.out.println(operLog.getBusinessType());

        switch (operLog.getBusinessType()) {
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
                businessTypeDes = BusinessType.GRANT.getDes();
                break;
            }
            case 6: {
                businessTypeDes = BusinessType.EXPORT.getDes();
                break;
            }
            case 7: {
                businessTypeDes = BusinessType.IMPORT.getDes();
                break;
            }
            case 8: {
                businessTypeDes = BusinessType.FORCE.getDes();
                break;
            }
            default: {
                businessTypeDes = "处理失败！";
            }
        }
        operLog.setBusinessTypeDes(businessTypeDes);

        // 保存到数据库中
        this.save(operLog);

    }

    /**
     * 删除日志文件
     */
    public boolean deleteLogFile() {

        boolean flag = false;

        File sysLogFile = new File(sysRunLogName);
        boolean delete = sysLogFile.delete();

        File userLogFile = new File(path + logName);
        boolean delete1 = userLogFile.delete();

        if (delete && delete1) {
            flag = true;
        }

        return flag;
    }
}
