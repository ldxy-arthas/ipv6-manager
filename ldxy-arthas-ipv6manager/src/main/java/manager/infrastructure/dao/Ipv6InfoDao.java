package manager.infrastructure.dao;

import manager.domain.ipv6.model.entity.TIpv6Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface Ipv6InfoDao extends JpaRepository<TIpv6Info, String> {
}
