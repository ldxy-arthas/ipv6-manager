package manager.interfaces.Admin;

import lombok.RequiredArgsConstructor;
import manager.application.AdminServiceManager;
import manager.domain.system.model.dto.AdminRequestDTO;
import manager.domain.system.model.vo.AdminResponseVO;
import manager.infrastructure.common.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author yuluo
 *@createTime 2023-01-29  12:22
 */

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminServiceManager serviceManager;

	@GetMapping("/")
	public Result<AdminResponseVO> getAll() {
		return serviceManager.getUser();
	}

	@PostMapping("/delete")
	public Result<AdminResponseVO> delete(
			@RequestBody AdminRequestDTO requestDTO
	) {
		return serviceManager.deleteUser(requestDTO);
	}

	@PostMapping("/add")
	public Result<AdminResponseVO> add(
			@RequestBody AdminRequestDTO requestDTO
	) {
		return serviceManager.addUser(requestDTO);
	}

	@PostMapping("/modify")
	public Result<AdminResponseVO> modify(
			@RequestBody AdminRequestDTO requestDTO
	) {
		return serviceManager.modifyUser(requestDTO);
	}

}
