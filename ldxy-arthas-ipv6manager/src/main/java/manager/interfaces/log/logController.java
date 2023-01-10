package manager.interfaces.log;

import lombok.RequiredArgsConstructor;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.common.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-09  13:10
 * @Description: log 表现层
 */

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class logController {

    private final LogService logService;

    @GetMapping("/")
    public Result<?> getLogs() {

        return Result.success(logService.getLogs());
    }


    @DeleteMapping("/")
    @Log(title = "删除数据库日志", businessType = BusinessType.DELETE)
    public Result<?> deleteDBLog() {

        return Result.success(logService.deleteDBLog());
    }

}
