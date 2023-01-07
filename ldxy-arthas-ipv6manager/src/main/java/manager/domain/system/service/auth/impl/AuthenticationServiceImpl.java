package manager.domain.system.service.auth.impl;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.dao.UserDao;
import manager.infrastructure.utils.JwtService;
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

    private final UserDao repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseVO register(RegisterRequestDTO request) {

        var user = TUser.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

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
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseVO.builder()
                .token(jwtToken)
                .build();
    }
}
