package manager.interfaces.management;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.application.WebSiteExamineService;
import manager.infrastructure.common.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ipv6Manager")
@RequiredArgsConstructor

public class Ipv6ManagerController {

    @Resource
    WebSiteExamineService ipv6Service;

    @RequestMapping("/checkWebSiteAddress/{webSiteAddress}")
    public Result<Object> checkWebSiteAddress(@PathVariable String webSiteAddress){
        Result<Object> objectResult = ipv6Service.checkwebSiteAddress(webSiteAddress);
        return objectResult;
    }

}
