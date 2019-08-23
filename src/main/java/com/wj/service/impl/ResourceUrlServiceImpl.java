package com.wj.service.impl;

import com.wj.entity.ResourceURL;
import com.wj.repository.ResourceUrlRepository;
import com.wj.service.ResourceUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jun.wang
 * @title: ResourceUrlServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/14 15:41
 */

@Service
public class ResourceUrlServiceImpl implements ResourceUrlService {

    @Autowired
    private ResourceUrlRepository resourceUrlRepository;

    @Autowired
    private RedisService redisService;

    public static final String OPEN_RESOURCE_URL_KEY = "GATEWAY:OPEN_RESOURCE";
    public static final String PRIVATE_RESOURCE_URL_KEY = "GATEWAY:PRIVATE_RESOURCE";

    @Override
    public List<ResourceURL> getAllResourceUrl() {
        List<ResourceURL> resourceUrlList = resourceUrlRepository.getAllResourceUrl();
        Map<Integer, List<ResourceURL>> resourceTypeMap = resourceUrlList.stream().collect(Collectors.groupingBy(ResourceURL::getResourceType));
        List<ResourceURL> openResourceUrlList = resourceTypeMap.get(-1);
        List<ResourceURL> privateResourceUrlList = resourceTypeMap.get(0);
        redisService.set(OPEN_RESOURCE_URL_KEY, openResourceUrlList);
        redisService.set(PRIVATE_RESOURCE_URL_KEY, privateResourceUrlList);
        return resourceUrlList;
    }
}
