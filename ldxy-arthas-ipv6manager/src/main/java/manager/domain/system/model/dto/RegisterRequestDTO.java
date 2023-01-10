package manager.domain.system.model.dto;

import lombok.*;
import manager.domain.system.model.entity.TUser;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  12:08
 * @Description: 用户注册DTO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    private String name;
    private String password;
    private String region;

}
