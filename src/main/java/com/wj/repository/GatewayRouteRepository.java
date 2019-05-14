package com.wj.repository;

import com.wj.entity.GatewayRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    Page<GatewayRoute> findAllByDeleteFlag(int deleteFlag, Pageable pageable);
    List<GatewayRoute> findAllByIdAndDeleteFlag(String id, int deleteFlag, Pageable pageable);
}
