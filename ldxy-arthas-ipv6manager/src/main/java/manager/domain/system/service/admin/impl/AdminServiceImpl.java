package manager.domain.system.service.admin.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AdminRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AdminResponseVO;
import manager.domain.system.service.admin.AdminService;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.utils.LoggerService;
import manager.repository.impl.SystemRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *@author yuluo
 *@createTime 2023-01-29  11:29
 */

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final PasswordEncoder passwordEncoder;

	private final SystemRepository systemRepository;

	@Override
	public AdminResponseVO addUser(AdminRequestDTO requestDTO) {

		var user = TUser.builder()
				.username(requestDTO.getUsername())
				.password(passwordEncoder.encode(requestDTO.getPassword()))
				.region(requestDTO.getRegion())
				.role(Role.USER)
				.build();

		TUser save = systemRepository.getUserDao().save(user);

		boolean flag = false;
		if (Objects.nonNull(save)) {
			flag = true;
		}

		LoggerService.authenticationServiceImplLogger.info("用户: {} 添加成功！", requestDTO.getUsername());

		return AdminResponseVO.builder()
				.username(user.getUsername())
				.password(requestDTO.getPassword())
				.flag(flag)
				.build();
	}

	@Override
	public AdminResponseVO modifyUser(AdminRequestDTO requestDTO) {

		System.out.println(requestDTO.getId());

		TUser tUser = systemRepository.getUserDao().findById(requestDTO.getId()).get();

		if (requestDTO.getUsername() != null) {
			tUser.setUsername(requestDTO.getUsername());
		}
		if (requestDTO.getPassword() != null) {
			tUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
		}
		if (requestDTO.getRegion() != null) {
			tUser.setRegion(requestDTO.getRegion());
		}

		systemRepository.getUserDao().save(tUser);

		return AdminResponseVO.builder()
				.uId(tUser.getId())
				.flag(true)
				.username(tUser.getUsername())
				.build();
	}

	@Override
	public AdminResponseVO getUser() {

		List<TUser> userList = systemRepository.getUserDao().findAll()
				.stream()
				.map((item) -> TUser.builder()
						.username(item.getUsername())
						.createTime(item.getCreateTime())
						.updateTime(item.getUpdateTime())
						.role(item.getRole())
						.region(item.getRegion())
						.id(item.getId())
						.build()).collect(Collectors.toList());

		return AdminResponseVO.builder()
				.userList(userList)
				.build();
	}

	@Override
	public AdminResponseVO deleteUser(AdminRequestDTO requestDTO) {

		systemRepository.getUserDao().deleteById(requestDTO.getId());

		return AdminResponseVO.builder()
				.flag(true)
				.username(requestDTO.getUsername())
				.build();
	}

}
