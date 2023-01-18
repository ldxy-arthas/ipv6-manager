package manager.interfaces.log;

import lombok.RequiredArgsConstructor;
import manager.application.SystemServiceManager;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.entity.TUser;
import manager.infrastructure.Enum.Role;
import manager.infrastructure.common.Result;
import manager.infrastructure.utils.JwtService;
import manager.repository.impl.SystemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

import static manager.infrastructure.utils.Security.SecurityUtils.decrypt;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-09  13:10
 * @Description: log 表现层
 */

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class LogController {


    private final SystemRepository systemRepository;

    private final SystemServiceManager systemServiceManager;

    private final JwtService jwtService;

    @GetMapping("/")
    public Result<?> getLogs(@RequestBody PageTagDao page) {

        return Result.success(systemServiceManager.getLog(page));
    }


    @DeleteMapping("/")
    public Result<?> deleteDBLog() {

        return Result.success(systemServiceManager.deleteDBLog());
    }

    /**
     * 因为种种原因，这么写了！
     * @param page dto 对象
     * @return 分页数据
     */
    @GetMapping("/getLog")
    public Object findAll(@RequestBody PageTagDao page){

        System.err.println(page.getToken());

        String decrypt;
        try {
            decrypt = decrypt(page.getToken());
        } catch (InvalidAlgorithmParameterException |
                 NoSuchPaddingException |
                 IllegalBlockSizeException |
                 BadPaddingException |
                 InvalidKeyException |
                 NoSuchAlgorithmException e) {
            throw new RuntimeException("Log controller token 解密失败！");
        }

        String username = jwtService.extractUsername(decrypt);

        // 用户操作日志只能管理员查看 鉴权
        Optional<TUser> curUser = systemRepository.getUserDao().findByUsername(username);
        if (
                curUser.isPresent()
                        &&
                !Objects.equals(curUser.get().getRole(), Role.ADMIN)
        ) {
            throw new RuntimeException("无权查看操作日志！");
        }

        Pageable pageable = PageRequest.of(page.getPageNum(), page.getPageSize());
        Page<TLog> users= systemRepository.getLogDao().findAll(pageable);

        return users.getContent();
    }


}
