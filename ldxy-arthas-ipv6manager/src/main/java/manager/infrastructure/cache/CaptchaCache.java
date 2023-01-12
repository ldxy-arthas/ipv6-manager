package manager.infrastructure.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class CaptchaCache {

    /**
     * 默认缓存时长
     */
    private static final long DEFAULT_TIMEOUT = DateUnit.MINUTE.getMillis();
    /**
     * 默认清理间隔时间
     */
    private static final long CLEAN_TIMEOUT = 5 * DateUnit.MINUTE.getMillis();
    /**
     * 缓存对象
     */
    private static final TimedCache<String, Object> TIMED_CACHE = CacheUtil.newTimedCache(DEFAULT_TIMEOUT);

    static {
        //启动定时任务
        TIMED_CACHE.schedulePrune(CLEAN_TIMEOUT);
    }

    public static void set(String key, Object value) {
        TIMED_CACHE.put(key, value);
    }

    public static void set(String key, Object value, long expire) {
        TIMED_CACHE.put(key, value, expire);
    }

    /**
     * 获取并重新计算过期时间
     */
    public static Object getWithUpdateLastAccess(String key) {
        return TIMED_CACHE.get(key);
    }

    /**
     * 获取
     *
     * @param key key
     * @return value
     */
    public static Object get(String key) {
        return TIMED_CACHE.get(key, false);
    }

    public static Set<String> keySet() {
        return TIMED_CACHE.keySet();
    }


    public static void remove(String key) {
        TIMED_CACHE.remove(key);
    }

    public static void clear() {
        TIMED_CACHE.clear();
    }

    public static class Constants {
        public static final String DICT_LIST_PREFIX = "dict:list:";
        public static final String DICT_ONE_PREFIX = "dict:one:";
        public static final String DICT_MAP_PREFIX = "dict:map:";

        public static final String SCRIPT = "script:";
    }
}


