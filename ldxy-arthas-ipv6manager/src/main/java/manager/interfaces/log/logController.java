package manager.interfaces.log;

import lombok.RequiredArgsConstructor;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/delete")
    @Log(title = "删除数据库日志", businessType = BusinessType.DELETE)
    public Result<?> deleteDBLog() {

        return Result.success(logService.deleteDBLog());
    }

}
