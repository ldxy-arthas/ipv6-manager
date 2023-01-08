package manager.infrastructure.Enum;

/**
 * @Author: yuluo
 * @CreateTime: 2022-10-01  19:39
 * @Description: 业务操作类型
 */
public enum BusinessType
{
    /**
     * 其它
     */
    OTHER("其他"),

    /**
     * 新增
     */
    INSERT("新增"),

    /**
     * 查询
     */
    SELECT("查询"),

    /**
     * 修改
     */
    UPDATE("修改"),

    /**
     * 删除
     */
    DELETE("删除"),

    /**
     * 分配ipv6
     */
    ASSIGNIPv6("分配ipv6"),
    ;

    private String des;

    BusinessType(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}

