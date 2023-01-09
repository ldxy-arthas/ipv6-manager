package manager.domain.ipv6.examine;

import cn.hutool.core.util.ObjectUtil;
import manager.domain.ipv6.model.entity.TWebsiteInfo;
import org.springframework.stereotype.Component;

@Component
public class WebSiteExamine extends DefaultExamine{
    public String checkWebSite(String webSiteUrl){
        String result = "0";
        //检查网站是否是ipv6
        String res = super.WebSiteSupportIpv6(webSiteUrl);
        if (res.equals("0")){
            result = "0";
        }
        if (!res.equals("0")){
            TWebsiteInfo websiteInfo = TWebsiteInfo.builder()
                    .ipv6Address(res)
                    .websiteInfoAddress(webSiteUrl)
                    .ipv6Status("1").build();
            if (ObjectUtil.isNull(ipv6Repository.getWebsiteInfoDao().findByWebsiteInfoAddress(webSiteUrl))){
                ipv6Repository.getWebsiteInfoDao().save(websiteInfo);
            }
            result="1";
        }
        return result;
    }
}
