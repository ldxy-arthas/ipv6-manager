package manager.domain.ipv6.examine;

import manager.domain.ipv6.model.entity.TIpv6Info;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Ipv6Examine extends DefaultExamine {
    public List<TIpv6Info> findTIpv6InfosLikeRegion(String region){
        List<TIpv6Info> ipv6InfoList;
        ipv6InfoList = ipv6Repository.getIpv6InfoDao().findTIpv6InfosLikeRegion(region);
        return ipv6InfoList;
    }
}
