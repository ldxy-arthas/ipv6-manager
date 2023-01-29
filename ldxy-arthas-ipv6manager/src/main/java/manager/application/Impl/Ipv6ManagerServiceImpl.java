package manager.application.Impl;

import jakarta.annotation.Resource;
import manager.application.Ipv6ManagerService;
import manager.domain.ipv6.model.entity.TIpv6Info;
import manager.repository.impl.Ipv6Repository;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Ipv6ManagerServiceImpl implements Ipv6ManagerService {
    @Resource
    Ipv6Repository ipv6Repository;
    public List<TIpv6Info> findTIpv6InfosByLikeRegion(String region){
        return ipv6Repository.getIpv6InfoDao().findTIpv6InfosByRegionLike(region);
    }
}

