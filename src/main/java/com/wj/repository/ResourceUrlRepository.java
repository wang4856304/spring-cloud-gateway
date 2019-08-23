package com.wj.repository;

import com.wj.entity.GatewayRoute;
import com.wj.entity.ResourceURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jun.wang
 * @title: ResourceUrlRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/14 15:38
 */
public interface ResourceUrlRepository extends JpaRepository<ResourceURL, String> {

    @Query("select r from ResourceURL r where r.deleteFlag=0 order by r.createAt desc")
    List<ResourceURL> getAllResourceUrl();
}
