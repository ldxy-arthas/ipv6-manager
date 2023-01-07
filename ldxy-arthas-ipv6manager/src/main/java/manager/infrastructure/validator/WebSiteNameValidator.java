package manager.infrastructure.validator;

import lombok.RequiredArgsConstructor;
import manager.domain.ipv6.model.entity.TWebsiteInfo;
import manager.domain.system.model.entity.TUser;
import manager.infrastructure.common.Exception.StatusFailException;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:20
 * @Description: TODO
 */

@Component
public class WebSiteNameValidator {

    private final String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    public void validateDomain(TWebsiteInfo websiteInfo) throws StatusFailException {

        if (!websiteInfo.getWebsiteInfoAddress().matches(regex)) {
            throw new StatusFailException("网站域名不合法！");
        }

        if (websiteInfo.getWebsiteInfoAddress().getBytes().length > 63) {
            throw new StatusFailException("域名超出最大长度限制！");
        }

    }

}
