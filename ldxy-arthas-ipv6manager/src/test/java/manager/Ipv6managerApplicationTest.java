package manager;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.domain.ipv6.model.entity.TIpv6Info;
import manager.repository.impl.Ipv6Repository;
import manager.repository.impl.SystemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
@RequiredArgsConstructor
class Ipv6managerApplicationTest {

    private final SystemRepository systemRepository;
    private final Ipv6Repository ipv6Repository;

    @Test
    void testDao() {

        var ipv6 = TIpv6Info.builder()
                .ipv6("test1234456767")
                .isUsed("0")
                .regionId("234")
                .build();
        TIpv6Info save = ipv6Repository.getIpv6InfoDao().save(ipv6);
//        TIpv6Info save = ipv6Repository;
        System.out.println();
    }

}
