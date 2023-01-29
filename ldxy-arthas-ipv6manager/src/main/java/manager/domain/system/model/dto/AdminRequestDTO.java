package manager.domain.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@author yuluo
 *@createTime 2023-01-29  12:43
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {

	private String id;

	private String username;

	private String password;

	private String region;

}
