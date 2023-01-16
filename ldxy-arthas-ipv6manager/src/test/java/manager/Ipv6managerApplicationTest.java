package manager;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.domain.ipv6.model.entity.TIpv6Info;
import manager.domain.system.model.entity.TUser;
import manager.repository.impl.Ipv6Repository;
import manager.repository.impl.SystemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@SpringBootTest
@RequiredArgsConstructor
class Ipv6managerApplicationTest {

    @Resource
    private  SystemRepository systemRepository;
    private  Ipv6Repository ipv6Repository;

    @Test
    void testDao() {
        Pageable pageable = PageRequest.of(2, 5);
        Page<TUser> users= systemRepository.getUserDao().findAll(pageable);
//        TIpv6Info save = ipv6Repository;
        System.out.println(users.getTotalPages());
    }

}
