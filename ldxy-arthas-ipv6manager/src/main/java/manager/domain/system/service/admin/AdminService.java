package manager.domain.system.service.admin;

import manager.domain.system.model.dto.AdminRequestDTO;
import manager.domain.system.model.vo.AdminResponseVO;

/**
 *@author yuluo
 *@createTime 2023-01-29  11:29
 */
public interface AdminService {

	AdminResponseVO addUser(AdminRequestDTO requestDTO);

	AdminResponseVO deleteUser(AdminRequestDTO requestDTO);

	AdminResponseVO modifyUser(AdminRequestDTO requestDTO);

	AdminResponseVO getUser();

}
