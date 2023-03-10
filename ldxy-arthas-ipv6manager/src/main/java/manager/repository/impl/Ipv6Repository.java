package manager.repository.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import manager.infrastructure.dao.Ipv6InfoDao;
import manager.infrastructure.dao.WebsiteInfoDao;
import manager.repository.DefaultRepository;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Ipv6Repository extends DefaultRepository {

    private final Ipv6InfoDao ipv6InfoDao;
    protected final WebsiteInfoDao websiteInfoDao;

 }
