package manager.system;

import jakarta.annotation.Resource;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-18  20:52
 * @Description: TODO
 */

@SpringBootTest
public class testAuth {

    @Resource
    private AuthenticationService authenticationService;

    @Test
    void testLogin() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
        requestDTO.setUsername("yuluo");
        requestDTO.setPassword("wert123");

        AuthenticationResponseVO authenticate = authenticationService.authenticate(requestDTO);
        System.out.println(authenticate);
    }

}
