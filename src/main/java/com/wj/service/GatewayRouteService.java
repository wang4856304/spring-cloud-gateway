package com.wj.service;

import com.wj.entity.GatewayRoute;

import java.util.List;

/**
 * @author jun.wang
 * @title: GatewayRouteService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/8 14:08
 */
public interface GatewayRouteService {
    List<GatewayRoute> findAll();
}
