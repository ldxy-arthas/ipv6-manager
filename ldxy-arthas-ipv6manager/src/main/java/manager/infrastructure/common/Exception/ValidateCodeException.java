package manager.infrastructure.common.Exception;

/**
 * @Author: yuluo
 * @CreateTime: 2022-09-21  13:38
 * @Description: TODO
 */

public class ValidateCodeException extends RuntimeException{
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
