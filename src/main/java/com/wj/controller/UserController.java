package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.Response;
import com.wj.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jun.wang
 * @title: UserController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/22 11:40
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    private final static String createTokenUrl = "http://localhost:9920/oauth/token";

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password) {
        Response response = new Response();
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password", password);
        param.put("grant_type", "password");
        param.put("client_id", "client_2");
        param.put("client_secret", "123456");
        try {
            String result = HttpClientUtil.httpPostRequest(createTokenUrl, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("error")) {
                logger.error(result);
                throw new RuntimeException("login fail");
            }
            response.setData(jsonObject);
            return  buildSuccessResponse(response);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/createToken")
    public Object createToken(@RequestParam String clientId, @RequestParam String clientSecret) {
        Response response = new Response();
        Map<String, Object> param = new HashMap<>();
        param.put("grant_type", "client_credentials");
        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        try {
            String result = HttpClientUtil.httpPostRequest(createTokenUrl, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("error")) {
                logger.error(result);
                throw new RuntimeException("login fail");
            }
            response.setData(jsonObject);
            return  buildSuccessResponse(response);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}