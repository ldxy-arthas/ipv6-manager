package manager.domain.system.service.auth;

import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.vo.AuthenticationResponseVO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:14
 * @Description: TODO
 */

public interface AuthenticationService {

    AuthenticationResponseVO register(RegisterRequestDTO request) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    AuthenticationResponseVO authenticate(AuthenticationRequestDTO request) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    Boolean logout(AuthenticationRequestDTO requestDTO);
}
