package com.wj.service.impl;

import com.wj.entity.GatewayRoute;
import com.wj.repository.GatewayRouteRepository;
import com.wj.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author jun.wang
 * @title: GatewayRouteServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/8 14:14
 */

@Service
public class GatewayRouteServiceImpl implements GatewayRouteService {

    @Autowired
    private GatewayRouteRepository gatewayRouteRepository;

    @Override
    public List<GatewayRoute> findAll() {
        /*List<GatewayRoute> list = gatewayRouteRepository.findAll();
        if (null == list) {
            return Collections.emptyList();
        }*/
        return gatewayRouteRepository.findAllByDeleteFlag(0);
    }
}
