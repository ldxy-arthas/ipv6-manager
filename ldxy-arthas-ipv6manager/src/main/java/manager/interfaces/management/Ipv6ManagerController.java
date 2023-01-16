package manager.interfaces.management;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.application.WebSiteExamineService;
import manager.domain.system.model.entity.TUser;
import manager.infrastructure.common.Result;
import manager.repository.impl.Ipv6Repository;
import manager.repository.impl.SystemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/ipv6Manager")
@RequiredArgsConstructor
public class Ipv6ManagerController {

    @Resource
    WebSiteExamineService ipv6Service;

    @Resource
    SystemRepository systemRepository;

    @RequestMapping("/checkWebSiteAddress/{webSiteAddress}")
    public Result<Object> checkWebSiteAddress(@PathVariable String webSiteAddress){
        Result<Object> objectResult = ipv6Service.checkwebSiteAddress(webSiteAddress);
        return objectResult;
    }

    @RequestMapping("/findAll/{page}/{size}")
    public Object findAll(@PathVariable("page") String page, @PathVariable("size") String size){
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size));
        Page<TUser> users= systemRepository.getUserDao().findAll(pageable);
        return users.getContent();
    }
}
