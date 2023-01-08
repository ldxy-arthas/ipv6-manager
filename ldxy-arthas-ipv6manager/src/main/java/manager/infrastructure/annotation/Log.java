package manager.infrastructure.annotation;

import manager.infrastructure.Enum.BusinessType;

import java.lang.annotation.*;

/**
 * @Author: yuluo
 * @CreateTime: 2023-1-8  17:21
 * @Description: TODO
 */


@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}

