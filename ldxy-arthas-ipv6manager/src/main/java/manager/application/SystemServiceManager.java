package manager.application;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.model.vo.VerifyCodeVo;
import manager.domain.system.service.auth.AuthenticationService;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.Enum.ResultEnum;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.captcha.CaptchaService;
import manager.infrastructure.common.Exception.StatusFailException;
import manager.infrastructure.common.Result;
import manager.infrastructure.utils.LoggerService;
import manager.infrastructure.validator.UserValidator;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    private final CaptchaService captchaService;

    public Result<AuthenticationResponseVO> login(AuthenticationRequestDTO requestDTO) {

        try {
            userValidator.validateUserLogin((TUser.builder()
                    .username(requestDTO.getUsername())
                    .password(requestDTO.getPassword())
                    .build()
            ));
        } catch (StatusFailException e) {
            return Result.failed(ResultEnum.AUTHENTICATE_FAILED, e.getMessage());
        }

        if (verifyCode(requestDTO.getCodeId(), requestDTO.getCode())) {
            LoggerService.systemServiceManagerLogger.warn("用户：{} 验证码验证失败！", requestDTO.getUsername());
            return Result.failed(ResultEnum.VERIFY_CODE_FAILED, ResultEnum.VERIFY_CODE_FAILED.getMessage());
        }

        return Result.success(authenticationService.authenticate(requestDTO));
    }

    public Result<Boolean> logout(AuthenticationRequestDTO requestDTO) {
        return Result.success(authenticationService.logout(requestDTO));
    }

    public Result<AuthenticationResponseVO> register(RegisterRequestDTO requestDTO) {

        try {
            userValidator.validateUserRegister(TUser.builder()
                    .username(requestDTO.getName())
                    .password(requestDTO.getPassword())
                    .region(requestDTO.getRegion())
                    .build()
            );
        } catch (StatusFailException e) {
            return Result.failed(e.getMessage());
        }

        if (verifyCode(requestDTO.getCodeId(), requestDTO.getCode())) {
            LoggerService.systemServiceManagerLogger.warn("用户：{} 验证码验证失败！", requestDTO.getName());
            return Result.failed(ResultEnum.VERIFY_CODE_FAILED, ResultEnum.VERIFY_CODE_FAILED.getMessage());
        }

        return Result.success(authenticationService.register(requestDTO));
    }

    public Result<VerifyCodeVo> getVerifyCode() {

        VerifyCodeVo verifyCodeVo;
        try {
            verifyCodeVo = captchaService.getVerifyCode();
        } catch (
                NoSuchPaddingException |
                IllegalBlockSizeException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException e) {
            throw new RuntimeException("验证码加密异常！");
        }

        return Result.success(verifyCodeVo);
    }

    public Result<?> getLog() {

        return Result.success(logService.getLogs());
    }

    @Log(title = "删除数据库日志", businessType = BusinessType.DELETE)
    public Result<?> deleteDBLog() {

        LoggerService.systemServiceManagerLogger.info("删除日志数据成功！");

        return Result.success(logService.deleteDBLog());
    }

    private boolean verifyCode(String codeId, String code) {

        try {
            return captchaService.checkCode(codeId, code);
        } catch (
                InvalidAlgorithmParameterException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
