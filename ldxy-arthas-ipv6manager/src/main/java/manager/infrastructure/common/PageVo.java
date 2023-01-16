package manager.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.domain.system.model.entity.TLog;

import java.util.List;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-16  20:56
 * @Description: TODO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {

    long count;

    private List<TLog> logList;
}
