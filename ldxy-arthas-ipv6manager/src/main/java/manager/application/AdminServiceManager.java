package manager.application;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AdminRequestDTO;
import manager.domain.system.model.vo.AdminResponseVO;
import manager.domain.system.service.admin.AdminService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.common.Exception.UserAuthorizationException;
import manager.infrastructure.common.Result;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static manager.infrastructure.utils.LoggerService.AdminServiceManagerLogger;

/**
 *@author yuluo
 *@createTime 2023-01-29  12:02
 */

@Component
@RequiredArgsConstructor
public class AdminServiceManager {

	private final AdminService adminService;

	protected static void checkAuth() throws UserAuthorizationException {

		UserDetails curUser = getCurUser();
		// AdminServiceManagerLogger.info(curUser.getAuthorities().toString());
		if (!curUser.getAuthorities().toString().equals("[ADMIN]")) {
			throw new UserAuthorizationException("权限不足，无法进行此操作！");
		}
	}

	private static UserDetails getCurUser() {

		SecurityContext context = SecurityContextHolder.getContext();
		AdminServiceManagerLogger.info("从 SecurityContextHolder 获取到的登录用户信息为：{}",
				context.getAuthentication().getPrincipal());

		Authentication authentication = context.getAuthentication();

		return (UserDetails) authentication.getPrincipal();
	}

	public Result<AdminResponseVO> getUser() {

		try {
			checkAuth();
		}
		catch (UserAuthorizationException e) {
			throw new RuntimeException(e);
		}

		AdminResponseVO adminResponseVO = adminService.getUser();

		return Result.success(adminResponseVO);
	}

	@Log(title = "添加普通用户", businessType = BusinessType.INSERT)
	public Result<AdminResponseVO> addUser(AdminRequestDTO requestDTO) {

		try {
			checkAuth();
		}
		catch (UserAuthorizationException e) {
			throw new RuntimeException(e);
		}

		AdminResponseVO adminResponseVO = adminService.addUser(requestDTO);

		return Result.success(adminResponseVO);
	}

	@Log(title = "修改普通用户", businessType = BusinessType.UPDATE)
	public Result<AdminResponseVO> modifyUser(AdminRequestDTO requestDTO) {

		try {
			checkAuth();
		}
		catch (UserAuthorizationException e) {
			throw new RuntimeException(e);
		}

		AdminResponseVO adminResponseVO = adminService.modifyUser(requestDTO);

		return Result.success(adminResponseVO);
	}

	@Log(title = "删除普通用户", businessType = BusinessType.DELETE)
	public Result<AdminResponseVO> deleteUser(AdminRequestDTO requestDTO) {

		try {
			checkAuth();
		}
		catch (UserAuthorizationException e) {
			throw new RuntimeException(e);
		}

		AdminResponseVO adminResponseVO = adminService.deleteUser(requestDTO);

		return Result.success(adminResponseVO);
	}

}
