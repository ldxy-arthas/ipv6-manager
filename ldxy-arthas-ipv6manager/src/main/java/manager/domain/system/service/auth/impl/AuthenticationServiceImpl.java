package manager.domain.system.service.auth.impl;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.cache.LoginUserCache;
import manager.infrastructure.common.Exception.StatusFailException;
import manager.infrastructure.utils.JwtService;
import manager.infrastructure.validator.UserValidator;
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

    private final UserValidator userValidator;

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

        return AuthenticationResponseVO.builder()
                .token(jwtToken)
                .build();
    }
}
