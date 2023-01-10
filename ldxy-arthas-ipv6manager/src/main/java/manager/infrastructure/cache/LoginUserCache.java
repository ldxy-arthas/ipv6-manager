package manager.infrastructure.cache;

import cn.hutool.core.util.ObjectUtil;
import manager.domain.system.model.entity.TUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户缓存
 * k:token
 * v:用户
 */
@Configuration
@EnableScheduling
public class LoginUserCache {
    private final static ConcurrentHashMap<String, TUser> cache ;

    static {
            cache =  new ConcurrentHashMap<>(8);
    }
    public static void set(String k, TUser v){
        if (ObjectUtil.isNull(v)){
            throw new RuntimeException("值为空");
        }
        if (ObjectUtil.isNull(k)){
            throw new RuntimeException("键为空");
        }
        try{
            cache.put(k, v);
        }catch (Exception e){
            throw new RuntimeException(e + "获取异常");
        }
    }
    public static TUser get(String k){
        TUser user = new TUser();
        if (ObjectUtil.isNull(k)){
            throw new RuntimeException("键为空");
        }
        try{
            user = cache.get(k);
        }catch (Exception e){
            throw new RuntimeException(e + "获取异常");
        }
        return user;
    }

    public static TUser delete(String k){
        TUser user = new TUser();
        if (ObjectUtil.isNull(k)){
            throw new RuntimeException("键为空");
        }
        try{
            user = cache.remove(k);

        } catch (Exception e) {
            throw new RuntimeException(e + "删除异常");
        }
        return cache.remove(k);
    }
}
