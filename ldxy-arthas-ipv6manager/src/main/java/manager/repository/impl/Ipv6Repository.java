package manager.repository.impl;

import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import manager.domain.ipv6.model.entity.TWebsiteInfo;
import manager.infrastructure.dao.Ipv6InfoDao;
import manager.infrastructure.dao.RegionDao;
import manager.infrastructure.dao.RegionLevelDao;
import manager.infrastructure.dao.WebsiteInfoDao;
import manager.repository.DefaultRepository;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Ipv6Repository extends DefaultRepository {

    private final Ipv6InfoDao ipv6InfoDao;

    private final RegionDao regionDao;

    private final RegionLevelDao regionLevelDao;

    protected final WebsiteInfoDao websiteInfoDao;
}
