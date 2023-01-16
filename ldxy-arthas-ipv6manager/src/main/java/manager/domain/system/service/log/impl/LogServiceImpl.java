package manager.domain.system.service.log.impl;

import lombok.RequiredArgsConstructor;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.vo.LogOperationResponseVO;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.Enum.BusinessType;
import manager.infrastructure.common.PageVo;
import manager.repository.impl.SystemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yuluo
 * @CreateTime: 2022-10-01  17:52
 * @Description: TODO
 */

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final SystemRepository system;

    private final MongoTemplate mongoTemplate;

    /**
     * 对日志进行持久化操作
     *
     * @param operaLog 日志对象
     */
    public void saveLog(TLog operaLog) {

        String businessTypeDes;

        switch (operaLog.getBusinessType()) {
            case 0 -> businessTypeDes = BusinessType.OTHER.getDes();
            case 1 -> businessTypeDes = BusinessType.INSERT.getDes();
            case 2 -> businessTypeDes = BusinessType.SELECT.getDes();
            case 3 -> businessTypeDes = BusinessType.UPDATE.getDes();
            case 4 -> businessTypeDes = BusinessType.DELETE.getDes();
            case 5 -> businessTypeDes = BusinessType.ASSIGNIPv6.getDes();
            case 6 -> businessTypeDes = BusinessType.LOGIN.getDes();
            default -> businessTypeDes = "处理失败！";
        }
        operaLog.setBusinessTypeDes(businessTypeDes);

        // 保存用户日志操作到数据库中
        this.system.getLogDao().save(operaLog);

    }

    /**
     * 删除数据库用户操作日志
     */
    public LogOperationResponseVO deleteDBLog() {

        this.system.getLogDao().deleteAll();

        return LogOperationResponseVO.builder()
                .flag(true)
                .build();
    }

    /**
     * 日志数据使用分页显示，避免一次性传输过多数据
     */
    @Override
    public LogOperationResponseVO getLogs(PageTagDao page) {

        Pageable pageable = PageRequest.of(page.getPageNum() + 1, page.getPageSize());
        Query query = new Query();

        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "create_time")));

        long count = mongoTemplate.count(query.with(pageable), TLog.class);
        List<TLog> tLogs = mongoTemplate.find(query, TLog.class);

        System.out.println(count + " " + tLogs);

        var pageVo = PageVo.builder()
                .logList(tLogs)
                .count(count)
                .build();

        return LogOperationResponseVO.builder()
                .pageVo(pageVo)
                .build();
    }

}
