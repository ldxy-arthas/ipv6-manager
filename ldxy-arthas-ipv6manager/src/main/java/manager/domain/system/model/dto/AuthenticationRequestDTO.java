package manager.domain.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
