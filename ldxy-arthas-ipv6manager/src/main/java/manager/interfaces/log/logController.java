package manager.interfaces.log;

import lombok.RequiredArgsConstructor;
import manager.application.SystemServiceManager;
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

    private final SystemServiceManager systemServiceManager;

    @GetMapping("/")
    public Result<?> getLogs() {

        return Result.success(systemServiceManager.getLog());
    }


    @DeleteMapping("/")
    public Result<?> deleteDBLog() {

        return Result.success(systemServiceManager.deleteDBLog());
    }

}
