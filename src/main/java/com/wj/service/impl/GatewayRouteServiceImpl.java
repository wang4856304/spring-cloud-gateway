package com.wj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wj.entity.GatewayRoute;
import com.wj.entity.dto.GatewayRouteDto;
import com.wj.repository.GatewayRouteRepository;
import com.wj.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //{"name": "RequestRateLimiter", "args": {"key-resolver": "#{@remoteAddrKeyResolver}", "redis-rate-limiter.replenishRate": 1, "redis-rate-limiter.burstCapacity": 3}}

    @Override
    public List<GatewayRoute> findAll() {
        List<GatewayRoute> list = gatewayRouteRepository.findAllByDeleteFlag(0);
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public Page<GatewayRoute> findAll(Integer pageNo, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createAt");
        Pageable pageable = createPageable(pageNo, pageSize, sort);
        return gatewayRouteRepository.findAllByDeleteFlag(0, pageable);
    }

    @Override
    public List<GatewayRoute> findById(String id, Integer pageNo, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createAt");
        Pageable pageable = createPageable(pageNo, pageSize, sort);
        List<GatewayRoute> list = gatewayRouteRepository.findAllByIdAndDeleteFlag(id, 0, pageable);
        if (list == null) {
            return Collections.emptyList();
        }
        gatewayRouteRepository.findAll(pageable);
        return list;
    }

    @Override
    public int saveGatewayRoute(List<GatewayRouteDto> gatewayRouteList) {
        List<GatewayRoute> list = gatewayRouteRepository.saveAll(toGatewayRouteList(gatewayRouteList));
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private List<GatewayRoute> toGatewayRouteList(List<GatewayRouteDto> gatewayRouteList) {
        return gatewayRouteList.stream().map(gatewayRouteDto -> {
            GatewayRoute gatewayRoute = new GatewayRoute();
            gatewayRoute.setUri(gatewayRouteDto.getUri());
            JSONArray jsonArray = new JSONArray();
            JSONObject predicatesJson = new JSONObject();
            predicatesJson.put("name", "Path");
            Map<String, String> predicateParams = new HashMap<>(8);
            predicateParams.put("pattern", gatewayRouteDto.getPredicates());
            predicatesJson.put("args", predicateParams);
            jsonArray.add(predicatesJson);
            gatewayRoute.setPredicates(jsonArray.toJSONString());
            gatewayRoute.setFilters(gatewayRouteDto.getFilters());
            return gatewayRoute;
        }).collect(Collectors.toList());
    }

    private Pageable createPageable(Integer pageNo, Integer pageSize, Sort sort) {
        Pageable pageable;
        if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        pageNo = pageNo -1;
        if (sort != null) {
            pageable = PageRequest.of(pageNo, pageSize, sort);
        }
        else {
            pageable = PageRequest.of(pageNo, pageSize);
        }
        return pageable;
    }
}
