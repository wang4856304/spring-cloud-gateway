package com.wj.repository;

import com.wj.entity.GatewayRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jun.wang
 * @title: GatewayRouteRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/8 14:05
 */
public interface GatewayRouteRepository extends JpaRepository<GatewayRoute, String> {
    List<GatewayRoute> findAllByDeleteFlag(int deleteFlag);
}
