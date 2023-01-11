package manager.infrastructure.Enum;

import manager.infrastructure.common.IResult;

/**
 * @Author: yuluo
 * @CreateTime: 2022-08-26  14:36
 * @Description: 返回结果枚举类
 */
public enum ResultEnum implements IResult {

    /*接口调用状态*/
    SUCCESS(8291, "接口调用成功"),
    VALIDATE_FAILED(8292, "参数校验失败"),
    COMMON_FAILED(8293, "接口调用失败"),

    /*用户状态枚举*/
    FORBIDDEN(7001, "用户无权限访问资源"),
    REGISTER_FAILED(7002, "用户注册失败"),
    AUTHENTICATE_FAILED(7003, "用户登录验证失败"),
    USER_NOT_FOUND(7004, "用户不存在");

    /*ipv6状态枚举*/

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
