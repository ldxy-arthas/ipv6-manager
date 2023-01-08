package manager.infrastructure.aop;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import manager.domain.system.model.entity.TLog;
import manager.domain.system.model.entity.TUser;
import manager.domain.system.service.log.LogService;
import manager.infrastructure.annotation.Log;
import manager.infrastructure.utils.AspectjUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: yuluo
 * @CreateTime: 2023-1-08 17:49
 * @Description: 日志切面
 */

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final LogService operaLogService;

    private final RedisTemplate<String, TUser> redisTemplate;

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {

           // 获取当前的用户
            HttpServletRequest request = AspectjUtils.getRequest();
            String token = request.getHeader("Authorization").substring(7);
            TUser curUser = redisTemplate.opsForValue().get(token);
            log.info("用户：{}", curUser);

            // 日志记录
            TLog operaLog = new TLog();
            operaLog.setStatus(0);

            // 请求的IP地址
            String iP = JakartaServletUtil.getClientIP(request);
            if ("0:0:0:0:0:0:0:1".equals(iP)) {
                iP = "127.0.0.1";
            }
            operaLog.setOperIp(iP);
            operaLog.setOperUrl(request.getRequestURI());
            if (curUser != null) {
                operaLog.setOperName(curUser.getUsername());
            }
            if (e != null) {
                operaLog.setStatus(1);
                operaLog.setErrorMsg(AspectjUtils.substring(e.getMessage(), 0, 2000));
            }

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operaLog.setMethod(className + "." + methodName + "()");
            operaLog.setRequestMethod(request.getMethod());

            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operaLog, jsonResult);

            // 保存数据库
            operaLogService.saveLog(operaLog);

        } catch (Exception exp) {
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operaLog 操作日志
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, TLog operaLog, Object jsonResult) {

        // 设置操作业务类型
        operaLog.setBusinessType(log.businessType().ordinal());

        // 设置标题
        operaLog.setTitle(log.title());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 设置参数的信息
            setRequestValue(joinPoint, operaLog);
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && AspectjUtils.isNotNull(jsonResult)) {
            operaLog.setJsonResult(AspectjUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operaLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, TLog operaLog) {

        String requestMethod = operaLog.getRequestMethod();

        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operaLog.setOperParam(AspectjUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) AspectjUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operaLog.setOperParam(AspectjUtils.substring(paramsMap.toString(), 0, 2000));
        }

    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object object : paramsArray) {
                // 不为空 并且是不需要过滤的 对象
                if (Objects.nonNull(object) && !isFilterObject(object)) {
                    Object jsonObj = JSON.toJSON(object);
                    params.append(jsonObj.toString()).append(" ");
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param object 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object object) {
        Class<?> clazz = object.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) object;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) object;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return object instanceof MultipartFile || object instanceof HttpServletRequest
                || object instanceof HttpServletResponse || object instanceof BindingResult;
    }
}
