package manager.application;

import manager.infrastructure.common.Result;

public interface Ipv6Service {
    public Result<Object> checkwebSiteAddress(String webSiteAddress);
}
