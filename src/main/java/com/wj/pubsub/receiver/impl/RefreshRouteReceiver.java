package com.wj.pubsub.receiver.impl;

import com.wj.pubsub.receiver.Receiver;
import com.wj.service.RefreshService;
import com.wj.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jun.wang
 * @title: RefreshRouteReceiver
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/9 16:29
 */

@Service
public class RefreshRouteReceiver implements Receiver {
    private static Logger logger = LoggerFactory.getLogger(RefreshRouteReceiver.class);
    @Override
    public void receiveMessage(String message) {
        logger.debug("message={}", message);
        RefreshService refreshService = SpringContextUtil.getBean("refreshServiceImpl", RefreshService.class);
        if (refreshService != null) {
            refreshService.refreshRoute();
        }
    }
}
