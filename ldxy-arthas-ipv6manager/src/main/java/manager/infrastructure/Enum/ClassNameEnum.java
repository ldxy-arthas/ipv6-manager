package manager.infrastructure.Enum;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  14:01
 * @Description: TODO
 */

public enum ClassNameEnum {

    AUTHENTICATION_SERVICE_IMPL("manager.domain.system.service.auth.impl.AuthenticationServiceImpl"),
    LOG_SERVICE_IMPL("manager.domain.system.service.log.impl.LogServiceImpl"),
    SYSTEM_SERVICE_MANAGER("manager.application.SystemServiceManager"),
    CAPTCHA_SERVICE("manager.infrastructure.captcha.CaptchaService"),
    ADMIN_SERVICE("manager.application.AdminServiceManager");

    private String des;

    ClassNameEnum(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

}
