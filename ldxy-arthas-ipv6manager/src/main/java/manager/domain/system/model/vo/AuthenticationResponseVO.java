package manager.domain.system.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  12:07
 * @Description: TODO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseVO {

    private String token;

}
