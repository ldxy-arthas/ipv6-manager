package manager.application;

import manager.domain.ipv6.model.entity.TIpv6Info;

import java.util.List;

public interface Ipv6ManagerService {
    public List<TIpv6Info> findTIpv6InfosByLikeRegion(String region);
}
