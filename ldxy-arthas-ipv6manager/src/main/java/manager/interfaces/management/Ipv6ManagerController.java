package manager.interfaces.management;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import manager.application.Ipv6ManagerService;
import manager.application.WebSiteExamineService;
import manager.domain.ipv6.model.entity.TIpv6Info;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.entity.TUser;
import manager.infrastructure.common.Result;
import manager.repository.impl.Ipv6Repository;
import manager.repository.impl.SystemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ipv6Manager")
@RequiredArgsConstructor
public class Ipv6ManagerController {

    @Resource
    WebSiteExamineService webSiteExamineService;
    @Resource
    Ipv6ManagerService ipv6ManagerService;
    @Resource
    SystemRepository systemRepository;

    @RequestMapping("/checkWebSiteAddress/{webSiteAddress}")
    public Result<Object> checkWebSiteAddress(@PathVariable String webSiteAddress){
        Result<Object> objectResult = webSiteExamineService.checkwebSiteAddress(webSiteAddress);
        return objectResult;
    }

    @RequestMapping("/findAll/{page}/{size}")
    public Result<Object> findAll(@PathVariable("page") String page, @PathVariable("size") String size){
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size));
        Page<TUser> users= systemRepository.getUserDao().findAll(pageable);
        return Result.success(users.getContent());
    }
    @RequestMapping("/findByRegion")
    public Result<Object> findByRegion(@RequestBody String region){
        List<TIpv6Info> TIpv6Infos=ipv6ManagerService.findTIpv6InfosByLikeRegion(region);
        return Result.success(TIpv6Infos);
    }
}
