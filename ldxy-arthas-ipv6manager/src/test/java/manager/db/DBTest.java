package manager.db;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.application.SystemServiceManager;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.dto.RegisterRequestDTO;
import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.model.vo.LogOperationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.common.PageVo;
import manager.infrastructure.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-16  21:03
 * @Description: TODO
 */

@SpringBootTest
public class DBTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private LogService logService;

    @Resource
    private SystemServiceManager manager;

    @Resource
    private AuthenticationService authenticationService;

    @Test
    void testFind() {

        Query query = new Query();

        long count = mongoTemplate.count(query, TLog.class);
        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "create_time")));

        List<TLog> tLogs = mongoTemplate.find(query, TLog.class);

        tLogs.forEach(System.out::println);
        System.out.println(count);
    }

    @Test
    void testGetLogs() {
        PageTagDao pageTagDao = new PageTagDao();
        pageTagDao.setPageNum(1);
        pageTagDao.setPageSize(5);

        LogOperationResponseVO logs = logService.getLogs(pageTagDao);
        System.out.println(logs.toString());
    }

    @Test
    void insertUser() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var user = RegisterRequestDTO.builder()
                .password("wert123")
                .name("yuluo")
                .region("庆阳市")
                .build();

        AuthenticationResponseVO register = authenticationService.register(user);
        System.out.println(register + "添加成功！");
    }

    @Test
    void insertTLogData() {
        for (int i = 0; i < 10; i ++) {
            var log = TLog.builder()
                    .businessType(0)
                    .errorMsg("测试日志")
                    .method("POST")
                    .operLocation("test")
                    .hint("测试数据")
                    .requestMethod("POST")
                    .build();
            mongoTemplate.save(log);
        }
    }

}
