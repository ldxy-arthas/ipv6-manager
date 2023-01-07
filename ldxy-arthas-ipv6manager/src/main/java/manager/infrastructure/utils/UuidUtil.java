package manager.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
@Data
@AllArgsConstructor
public final class UuidUtil {

    public static String getUuid() {

        return UUID.randomUUID().toString().replace("-", "");
    }

}
