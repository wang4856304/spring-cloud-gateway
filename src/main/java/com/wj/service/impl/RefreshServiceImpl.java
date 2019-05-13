package com.wj.service.impl;

import com.wj.service.RefreshService;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author jun.wang
 * @title: RefreshServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/9 13:55
 */

@Service
public class RefreshServiceImpl implements RefreshService {

    private ApplicationEventPublisher publisher;

    @Override
    public void refreshRoute() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
