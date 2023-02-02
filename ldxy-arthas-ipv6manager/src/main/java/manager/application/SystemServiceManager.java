package manager.application;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.model.vo.LogOperationResponseVO;
import manager.domain.system.model.vo.VerifyCodeVo;
import manager.domain.system.service.auth.AuthenticationService;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.Enum.ResultEnum;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.captcha.CaptchaService;
import manager.infrastructure.common.Exception.StatusFailException;
import manager.infrastructure.common.Result;
import manager.infrastructure.utils.JwtService;
import manager.infrastructure.utils.LoggerService;
import manager.infrastructure.validator.UserValidator;
import manager.repository.impl.SystemRepository;

import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  13:44
 * @Description:
 */

@Component
@RequiredArgsConstructor
public class SystemServiceManager  {

	private final AuthenticationService authenticationService;

	private final LogService logService;

	private final UserValidator userValidator;

	private final CaptchaService captchaService;

	private final SystemRepository systemRepository;

	private final JwtService jwtService;

	public Result<AuthenticationResponseVO> login(AuthenticationRequestDTO requestDTO) {

		try {
			userValidator.validateUserLogin((TUser.builder()
					.username(requestDTO.getUsername())
					.password(requestDTO.getPassword())
					.build()
			));
		}
		catch (StatusFailException e) {
			return Result.failed(ResultEnum.AUTHENTICATE_FAILED, e.getMessage());
		}

		if (verifyCode(requestDTO.getCodeId(), requestDTO.getCode())) {
			LoggerService.systemServiceManagerLogger.warn("用户：{} 验证码验证失败！", requestDTO.getUsername());
			return Result.failed(ResultEnum.VERIFY_CODE_FAILED, ResultEnum.VERIFY_CODE_FAILED.getMessage());
		}

		AuthenticationResponseVO register;
		try {
			register = authenticationService.authenticate(requestDTO);
		}
		catch (
				NoSuchPaddingException |
				IllegalBlockSizeException |
				NoSuchAlgorithmException |
				BadPaddingException |
				InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		return Result.success(register);
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
		}
		catch (StatusFailException e) {
			return Result.failed(e.getMessage());
		}

		if (verifyCode(requestDTO.getCodeId(), requestDTO.getCode())) {
			LoggerService.systemServiceManagerLogger.warn("用户：{} 验证码验证失败！", requestDTO.getName());
			return Result.failed(ResultEnum.VERIFY_CODE_FAILED, ResultEnum.VERIFY_CODE_FAILED.getMessage());
		}

		AuthenticationResponseVO register;
		try {
			register = authenticationService.register(requestDTO);
		}
		catch (
				NoSuchPaddingException |
				IllegalBlockSizeException |
				NoSuchAlgorithmException |
				BadPaddingException |
				InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		return Result.success(register);
	}

	public Result<VerifyCodeVo> getVerifyCode() {

		VerifyCodeVo verifyCodeVo;
		try {
			verifyCodeVo = captchaService.getVerifyCode();
		}
		catch (
				NoSuchPaddingException |
				IllegalBlockSizeException |
				NoSuchAlgorithmException |
				BadPaddingException |
				InvalidKeyException e) {
			throw new RuntimeException("验证码加密异常！");
		}

		return Result.success(verifyCodeVo);
	}

	public Result<LogOperationResponseVO> getLog(PageTagDao page) {

		String username = jwtService.extractUsername(page.getToken());

		// 用户操作日志只能管理员查看 鉴权
		Optional<TUser> curUser = systemRepository.getUserDao().findByUsername(username);
		if (
				curUser.isPresent()
						&&
						!Objects.equals(curUser.get().getRole(), Role.ADMIN)
		) {
			throw new RuntimeException("无权查看操作日志！");
		}

		return Result.success(logService.getLogs(page));
	}

	@Log(title = "删除数据库日志", businessType = BusinessType.DELETE)
	public Result<LogOperationResponseVO> deleteDBLog() {

		LoggerService.systemServiceManagerLogger.info("删除日志数据成功！");

		return Result.success(logService.deleteDBLog());
	}

	private boolean verifyCode(String codeId, String code) {

		try {
			return captchaService.checkCode(codeId, code);
		}
		catch (
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
