package manager.infrastructure.common.Exception;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  20:24
 * @Description: TODO
 */

public class StatusFailException extends Exception{
    public StatusFailException() {
    }

    public StatusFailException(String message) {
        super(message);
    }

    public StatusFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusFailException(Throwable cause) {
        super(cause);
    }

    public StatusFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}