package manager.application;

import manager.infrastructure.common.Result;

public interface WebSiteExamineService {
    //检查网站是否是
    public Result<Object> checkwebSiteAddress(String webSiteAddress);
}
