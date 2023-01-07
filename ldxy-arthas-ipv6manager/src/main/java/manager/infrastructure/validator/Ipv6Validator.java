package manager.infrastructure.validator;

import lombok.RequiredArgsConstructor;
import manager.domain.ipv6.model.entity.TIpv6Info;
import manager.infrastructure.common.Exception.StatusFailException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.net.Inet6Address;
import java.net.UnknownHostException;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:19
 * @Description: TODO
 */

@Component
@RequiredArgsConstructor
public class Ipv6Validator {

    private final CommonValidator commonValidator;

    public void validateIpv6Address(TIpv6Info ipv6Info) throws StatusFailException, UnknownHostException {
        commonValidator.validateContentLength(ipv6Info.getIpv6(),"ipv6地址", 32);

        if (Inet6Address.getByName(ipv6Info.getIpv6()) instanceof Inet6Address) {
            throw new StatusFailException("不是有效的ipv6地址！");
        }
    }

}
