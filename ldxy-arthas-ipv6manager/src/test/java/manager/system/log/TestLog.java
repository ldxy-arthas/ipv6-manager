package manager.system.log;

import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import manager.domain.system.model.vo.LogOperationResponseVO;
import manager.domain.system.service.log.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-09  11:55
 * @Description: TODO
 */

@SpringBootTest
public class TestLog {

    @Resource
    private LogService logService;

    @Test
    void testDeleteSysLog() throws IOException {

        LogOperationResponseVO logOperationResponseVO = logService.deleteSysLogFile();
        Assert.isTrue(logOperationResponseVO.getFlag(), "删除系统运行日志失败！");
    }

}
