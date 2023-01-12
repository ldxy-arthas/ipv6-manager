package manager.interfaces.code;

import lombok.RequiredArgsConstructor;
import manager.application.SystemServiceManager;
import manager.domain.system.model.vo.VerifyCodeVo;
import manager.infrastructure.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  22:45
 * @Description: TODO
 */

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class VerifyCodeController {

    private final SystemServiceManager serviceManager;

    @GetMapping("/")
    public Result<VerifyCodeVo> getVerifyCode() {
        return serviceManager.getVerifyCode();
    }

}
