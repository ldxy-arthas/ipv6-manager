package manager.interfaces.auth;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.application.SystemServiceManager;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.infrastructure.common.Result;
import manager.repository.impl.SystemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  12:04
 * @Description: 用户相关业务
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationContrller {

    private final SystemServiceManager systemServiceManager;

    @PostMapping("/register")
    public Result<AuthenticationResponseVO> register(
        @RequestBody RegisterRequestDTO request
    ){
        return (systemServiceManager.register(request));
    }

    @PostMapping("/login")
    public Result<AuthenticationResponseVO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ){
        return (systemServiceManager.login(request));
    }

    @PostMapping("/logout")
    public Result<Boolean> logut(
            @RequestBody AuthenticationRequestDTO request
    ){
        return (systemServiceManager.logout(request));
    }

}
