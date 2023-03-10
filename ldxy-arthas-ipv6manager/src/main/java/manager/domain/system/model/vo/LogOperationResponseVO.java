package manager.domain.system.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.infrastructure.common.PageVo;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-09  11:24
 * @Description: TODO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogOperationResponseVO {

    private Boolean flag;

    private PageVo pageVo;

}

