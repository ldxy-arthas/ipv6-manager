package manager.domain.system.service.auth;

import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.infrastructure.common.Exception.StatusFailException;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:14
 * @Description: TODO
 */

public interface AuthenticationService {

    AuthenticationResponseVO register(RegisterRequestDTO request) throws StatusFailException;

    AuthenticationResponseVO authenticate(AuthenticationRequestDTO request);

}
