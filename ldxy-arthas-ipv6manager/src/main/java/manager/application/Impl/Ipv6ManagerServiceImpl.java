package manager.application.Impl;

import jakarta.annotation.Resource;
import manager.application.Ipv6ManagerService;
import manager.domain.ipv6.examine.Ipv6Examine;
import manager.domain.ipv6.model.entity.TIpv6Info;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Ipv6ManagerServiceImpl implements Ipv6ManagerService {
    @Resource
    Ipv6Examine ipv6Examine;
    public List<TIpv6Info> findTIpv6InfosLikeRegion(String region){
        return ipv6Examine.findTIpv6InfosLikeRegion(region);
    }
}

