package manager.controller;

import jakarta.annotation.Resource;
import manager.domain.system.model.dto.AuthenticationRequestDTO;
import manager.domain.system.model.dto.PageTagDao;
import manager.domain.system.model.vo.AuthenticationResponseVO;
import manager.domain.system.service.auth.AuthenticationService;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-18  18:57
 * @Description: 测试控制器请求
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Resource
    private AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);

    ResponseEntity<JSONObject> request(String method, String jsonPost, String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));

        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(jsonPost, httpHeaders);

        ResponseEntity<JSONObject> responseEntity;
        if (method.equalsIgnoreCase("POST")) {
            responseEntity = this.restTemplate.postForEntity(url, stringHttpEntity, JSONObject.class);
        } else {
            responseEntity = this.restTemplate.getForEntity(url, JSONObject.class);
        }

        logger.info("controller 测试结果：{}", responseEntity);

        return responseEntity;
    }

    @Test
    void testLogController() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        var user = AuthenticationRequestDTO.builder()
                .username("yuluo")
                .password("wert123")
                .build();

        AuthenticationResponseVO authenticate = authenticationService.authenticate(user);

        // error 红色显眼
        logger.error("用户：{} 登录获取token成功, token: {}", user.getUsername(), authenticate.getToken());

        var page = PageTagDao.builder()
                .pageNum(1)
                .pageSize(1)
                .token(authenticate.getToken())
                .build();

        this.request("GET", JSONObject.toJSONString(page), "/api/log/getLog");
    }

}
