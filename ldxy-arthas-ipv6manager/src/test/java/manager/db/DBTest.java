package manager.db;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.vo.LogOperationResponseVO;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.common.PageVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

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
        pageTagDao.setPageSize(1);

        LogOperationResponseVO logs = logService.getLogs(pageTagDao);
        System.out.println(logs.toString());
    }

}
