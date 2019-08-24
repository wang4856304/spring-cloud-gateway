package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.service.ResourceUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.wang
 * @title: ResourceUrlController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/21 15:12
 */

@RestController
@RequestMapping("api/url")
public class ResourceUrlController {


    @Autowired
    private ResourceUrlService resourceUrlService;

    @GetMapping("/getAllResourceUrl")
    public Object getAllResourceUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        jsonObject.put("msg", "success");
        jsonObject.put("data", resourceUrlService.getAllResourceUrl());
        return jsonObject;
    }
}
