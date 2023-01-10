package manager.application.Impl;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import manager.application.WebSiteExamineService;
import manager.domain.ipv6.examine.WebSiteExamine;
import manager.domain.ipv6.model.entity.TWebsiteInfo;
import manager.infrastructure.common.Result;
import manager.repository.impl.Ipv6Repository;
import org.springframework.stereotype.Component;

@Component
public class WebSiteExamineServiceImpl implements WebSiteExamineService {
    @Resource
    WebSiteExamine webSiteExamine;
    @Resource
    Ipv6Repository ipv6Repository;
    @Override
    public Result<Object> checkwebSiteAddress(String webSiteAddress) {
        //检查网站名字
        TWebsiteInfo websiteInfo = webSiteExamine.checkWebSite(webSiteAddress);
        //如果不是则返回0，不是支持ipv6的网站
        if (ObjectUtil.isNull(websiteInfo)){
            return Result.success("0");
        }
        //更新数据库
        ipv6Repository.getWebsiteInfoDao().save(websiteInfo);
        return Result.success(websiteInfo);
    }
}
