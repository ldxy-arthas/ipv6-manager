package manager.application;

import jakarta.annotation.Resource;
import manager.domain.ipv6.examine.Ipv6Examine;
import manager.infrastructure.common.Result;

public class Ipv6ServiceImpl implements Ipv6Service{
    @Resource
    Ipv6Examine ipv6Examine;
    @Override
    public Result<Object> checkwebSiteAddress(String webSiteAddress) {
        String result = ipv6Examine.WebSiteSupportIpv6(webSiteAddress);
        return Result.success(result);
    }
}
