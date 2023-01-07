package manager;

import jakarta.annotation.Resource;
import manager.domain.ipv6.model.entity.TIpv6Info;
import manager.infrastructure.dao.Ipv6InfoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = Ipv6managerApplicationTest.class)
class Ipv6managerApplicationTest {

    @Autowired
    Ipv6InfoDao ipv6InfoDao;
    @Test
    void contextLoads() {
        List<TIpv6Info> all = ipv6InfoDao.findAll();
        System.out.println(Arrays.toString(all.toArray()));
    }

}
