package com.wj.service;

import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author jun.wang
 * @title: RefreshService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/9 13:54
 */
public interface RefreshService extends ApplicationEventPublisherAware {
    void refreshRoute();
}
