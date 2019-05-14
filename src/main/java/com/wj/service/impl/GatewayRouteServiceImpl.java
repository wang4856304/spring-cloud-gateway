package com.wj.service.impl;

import com.wj.entity.GatewayRoute;
import com.wj.repository.GatewayRouteRepository;
import com.wj.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
