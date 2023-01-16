package manager.domain.ipv6.examine;

import cn.hutool.core.util.ObjectUtil;
import manager.domain.ipv6.model.entity.TWebsiteInfo;
import org.springframework.stereotype.Component;

@Component
public class WebSiteExamine extends DefaultExamine{

    public TWebsiteInfo checkWebSite(String webSiteUrl){
        //检查网站是否是ipv6
        String res = super.WebSiteSupportIpv6(webSiteUrl);
        if (res.equals("0")){
            return null;
        }
        //反查一下数据库，获取id没有的话id就没有
        TWebsiteInfo websiteInfo = ipv6Repository.getWebsiteInfoDao().findByWebsiteInfoAddress(webSiteUrl);
        websiteInfo.setIpv6Address(res);
        websiteInfo.setWebsiteInfoAddress(webSiteUrl);
        websiteInfo.setIpv6Status("1");
        return websiteInfo;
    }
}
