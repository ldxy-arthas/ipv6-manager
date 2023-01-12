package manager.domain.system.service.auth;

import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.vo.AuthenticationResponseVO;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:14
 * @Description: TODO
 */

public interface AuthenticationService {

    AuthenticationResponseVO register(RegisterRequestDTO request);

    AuthenticationResponseVO authenticate(AuthenticationRequestDTO request);

    Boolean logout(AuthenticationRequestDTO requestDTO);
}
