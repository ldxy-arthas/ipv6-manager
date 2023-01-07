package manager.infrastructure.validator;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.entity.TUser;
import manager.infrastructure.common.Exception.StatusFailException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:19
 * @Description: TODO
 */

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final CommonValidator commonValidator;

    public void validateUser(TUser user) throws StatusFailException {

        if (Objects.isNull(user.getUsername())) {
            throw new StatusFailException("用户名不能为空！");
        }
        if (Objects.isNull(user.getPassword())) {
            throw new StatusFailException("用户密码不能为空！");
        }
        if (Objects.isNull(user.getRegion())) {
            throw new StatusFailException("用户所在地区不能为空！");
        }

        commonValidator.validateContentLength(user.getPassword(), "用户密码", 10);
        commonValidator.validateContentLength(user.getUsername(), "用户名", 5);

    }
}
