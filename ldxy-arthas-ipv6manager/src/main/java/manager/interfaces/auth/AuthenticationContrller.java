package manager.interfaces.auth;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import manager.infrastructure.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  12:04
 * @Description: 用户相关业务
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationContrller {

    private final AuthenticationService service;

    @PostMapping("/register")
    public Result<AuthenticationResponseVO> register(
        @RequestBody RegisterRequestDTO request
    ){
        return Result.success(service.register(request));
    }

    @PostMapping("/authenticate")
    public Result<AuthenticationResponseVO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ){
        return Result.success(service.authenticate(request));
    }

}
