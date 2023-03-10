package manager.infrastructure.cache;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import manager.domain.system.model.entity.TUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户缓存
 * k:token
 * v:用户
 */

@Slf4j
@Configuration
@EnableScheduling
public class LoginUserCache {
    private final static ConcurrentHashMap<String, TUser> cache ;

    static {
        cache =  new ConcurrentHashMap<>(8);
    }
    public static void set(String k, TUser v) {

        if (ObjectUtil.isNull(v)){
            throw new RuntimeException("值为空");
        }
        if (ObjectUtil.isNull(k)){
            throw new RuntimeException("键为空");
        }
        try{
            log.info("用户：{} 登录成功，存入登录用户缓存，token：{}", v, k);
            cache.put(k, v);
        }catch (Exception e){
            throw new RuntimeException(e + "获取异常");
        }
    }
    public static TUser get(String k){
        TUser user;
        if (ObjectUtil.isNull(k)){
            throw new RuntimeException("键为空");
        }
        try{
            log.info("从缓存中获取登陆用户，token: {}", k);
            user = cache.get(k);
        }catch (Exception e){
            throw new RuntimeException(e + "获取异常");
        }
        return user;
    }

    public static boolean delete(String k){

        return ObjectUtil.isNull(k) && Objects.nonNull(cache.remove(k));
    }
}