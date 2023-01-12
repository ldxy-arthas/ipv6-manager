package manager.domain.system.model.dto;

import lombok.*;
import manager.domain.system.model.entity.TUser;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  12:23
 * @Description: TODO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

    private String username;

    private String password;

    private String codeId;

    private String code;

    private String token;

}
