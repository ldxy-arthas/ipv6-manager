package manager.domain.system.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.domain.system.model.entity.TUser;

/**
 *@author yuluo
 *@createTime 2023-01-29  12:45
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseVO {

	private String uId;

	private Boolean flag;

	private String username;

	public String password;

	public List<TUser> userList;

}
