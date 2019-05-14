package com.wj.service;

import com.wj.entity.GatewayRoute;
import org.springframework.data.domain.Page;

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
    Page<GatewayRoute> findAll(Integer pageNo, Integer pageSize);
    List<GatewayRoute> findById(String id, Integer pageNo, Integer pageSize);
}
