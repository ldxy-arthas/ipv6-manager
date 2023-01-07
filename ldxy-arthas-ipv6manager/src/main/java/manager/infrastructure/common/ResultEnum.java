package manager.infrastructure.common;



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
    FORBIDDEN(8294, "没有权限访问资源");

    /*用户状态枚举*/

    /*ipv6状态枚举*/

    private Integer code;
    private String message;

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
