package manager.infrastructure.common;

/**
 * @Author: yuluo
 * @CreateTime: 2022-08-26  14:48
 * @Description: 返回结果行为接口
 */

public interface IResult {

    /**
     * 获取code
     * @return
     */
    Integer getCode();

    /**
     * 获取描述
     * @return
     */
    String getMessage();

}
