package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.pubsub.publish.impl.RedisPublish;
import com.wj.pubsub.subscribe.Channel;
import com.wj.service.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.wang
 * @title: RefreshController
 * @projectName ownerpro
 * @description: 刷新路由
 * @date 2019/5/9 13:50
 */

@RestController
@RequestMapping("/api")
public class RefreshController {

    @Autowired
    private RefreshService refreshService;

    @Autowired
    private RedisPublish redisPublish;

    @RequestMapping("/refreshRoute")
    public Object refreshRoute() {
        redisPublish.publish(Channel.REFRESH_ROUTE, "message1111111111111");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        return jsonObject;
    }

}
