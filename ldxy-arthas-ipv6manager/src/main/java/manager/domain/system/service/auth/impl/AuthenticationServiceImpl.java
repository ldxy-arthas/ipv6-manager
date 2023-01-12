package manager.domain.system.service.auth.impl;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.cache.LoginUserCache;
import manager.infrastructure.utils.JwtService;
import manager.infrastructure.utils.LoggerService;
import manager.repository.impl.SystemRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  12:26
 * @Description: 用户相关业务
 */

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final SystemRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseVO register(RegisterRequestDTO request) {

        var user = TUser.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .region(request.getRegion())
                .role(Role.USER)
                .build();

        repository.getUserDao().save(user);

        var jwtToken = jwtService.generateToken(user);

        LoginUserCache.set(jwtToken, user);

        LoggerService.authenticationServiceImplLogger.info("用户: {} 注册成功！", request.getName());

        return AuthenticationResponseVO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseVO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.getUserDao().findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        LoginUserCache.get(jwtToken);

        LoggerService.systemServiceManagerLogger.info("用户: {} 登录成功！", request.getUsername());

        return AuthenticationResponseVO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public Boolean logout(AuthenticationRequestDTO requestDTO) {

        boolean delete = LoginUserCache.delete(requestDTO.getToken());

        if (delete) {
            LoggerService.authenticationServiceImplLogger.error("删除用户token失败！{}", delete);
            throw new RuntimeException("删除用户token失败！");
        }

        return true;
    }

}
