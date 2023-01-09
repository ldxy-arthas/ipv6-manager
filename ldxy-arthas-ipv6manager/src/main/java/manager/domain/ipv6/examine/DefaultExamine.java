package manager.domain.ipv6.examine;

import jakarta.annotation.Resource;
import manager.repository.impl.Ipv6Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

public abstract class DefaultExamine {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    protected Ipv6Repository ipv6Repository;
    /**
     *
     * @param webSiteUrl
     * @return 不是ipv6的化返回：0
     *          是的话返回ipv6地址
     */
    public  String WebSiteSupportIpv6(String webSiteUrl){
        String result = "0";
        try {
            //访问dns服务器查看网站是否经过 AAAA(IPv6 地址)的解析
            Lookup lookup = new Lookup("www.ipw.cn", Type.AAAA);
            lookup.run();
            //如果没有返回
            if (lookup.getResult() != Lookup.SUCCESSFUL){
                return result;
            }
            //截取该网站的ipv6地址
            Record[] answers = lookup.getAnswers();
            String[] split = answers[0].toString().split("\t");
            String webSiteIpv6 = split[split.length - 1];
            result = webSiteIpv6;
        }catch (Exception e){
            logger.info("网站检测失败");
            throw  new RuntimeException(e);
        }
        return result;
    }


}
