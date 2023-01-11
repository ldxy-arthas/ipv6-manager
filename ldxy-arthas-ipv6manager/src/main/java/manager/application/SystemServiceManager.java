package manager.application;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.Enum.ResultEnum;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.common.Exception.StatusFailException;
import manager.infrastructure.common.IResult;
import manager.infrastructure.common.Result;
import manager.infrastructure.utils.LoggerService;
import manager.infrastructure.validator.UserValidator;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  13:44
 * @Description:
 */

@Component
@RequiredArgsConstructor
public class SystemServiceManager extends DefaultServiceManager {

    private final AuthenticationService authenticationService;

    private final LogService logService;

    private final UserValidator userValidator;

    public Result<AuthenticationResponseVO> login(AuthenticationRequestDTO requestDTO) {

        try {
            userValidator.validateUser((TUser.builder()
                    .username(requestDTO.getUsername())
                    .password(requestDTO.getPassword())
                    .build()
            ));
        } catch (StatusFailException e) {
            return Result.failed(ResultEnum.AUTHENTICATE_FAILED, e.getMessage());
        }

        LoggerService.systemServiceManagerLogger.info("用户: {} 登录成功！", requestDTO.getUsername());

        return Result.success(authenticationService.authenticate(requestDTO));
    }

    public Result<AuthenticationResponseVO> register(RegisterRequestDTO requestDTO) {

        try {
            userValidator.validateUser(TUser.builder()
                    .username(requestDTO.getName())
                    .password(requestDTO.getPassword())
                    .region(requestDTO.getRegion())
                    .build()
            );
        } catch (StatusFailException e) {
            return Result.failed(e.getMessage());
        }

        LoggerService.systemServiceManagerLogger.info("用户: {} 注册成功！", requestDTO.getName());

        return Result.success(authenticationService.register(requestDTO));
    }

    public Result<?> getLog() {

        return Result.success(logService.getLogs());
    }

    @Log(title = "删除数据库日志", businessType = BusinessType.DELETE)
    public Result<?> deleteDBLog() {

        LoggerService.systemServiceManagerLogger.info("删除日志数据成功！");

        return Result.success(logService.deleteDBLog());
    }

}
