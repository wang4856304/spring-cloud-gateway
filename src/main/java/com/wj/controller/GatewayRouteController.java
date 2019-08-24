package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.GatewayRoute;
import com.wj.entity.Response;
import com.wj.entity.dto.GatewayRouteDto;
import com.wj.pubsub.publish.impl.RedisPublish;
import com.wj.pubsub.subscribe.Channel;
import com.wj.service.GatewayRouteService;
import com.wj.service.RefreshService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author jun.wang
 * @title: RefreshController
 * @projectName ownerpro
 * @description: 刷新路由
 * @date 2019/5/9 13:50
 */

@RestController
@RequestMapping("/api")
@Validated
public class GatewayRouteController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(GatewayRouteController.class);

    @Autowired
    private RefreshService refreshService;

    @Autowired
    private RedisPublish redisPublish;

    @Autowired
    private GatewayRouteService gatewayRouteService;

    @GetMapping("/refreshRoute")
    public Object refreshRoute() {
        redisPublish.publish(Channel.REFRESH_ROUTE, "message1111111111111");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        return jsonObject;
    }

    @GetMapping(path = "/queryRoutes", produces = "application/json")
    public Object queryRoutes(Integer pageNo, Integer pageSize) {

        logger.info("sssssssssss");
        return gatewayRouteService.findAll(pageNo, pageSize);
    }

    @GetMapping("/queryRoutesById")
    public Object queryRoutesById(@RequestParam String id, Integer pageNo, Integer pageSize) {

        return gatewayRouteService.findById(id, pageNo, pageSize);
    }

    @PostMapping("/saveGatewayRoute")
    public ResponseEntity<Response> saveGatewayRoute(@RequestBody List<@Valid GatewayRouteDto> gatewayRouteList) {
        gatewayRouteService.saveGatewayRoute(gatewayRouteList);
        Response response = new Response();
        return ResponseEntity.ok((Response)buildSuccessResponse(response));
    }
}
