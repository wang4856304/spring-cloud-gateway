package com.wj.pubsub.publish.impl;

import com.wj.pubsub.publish.Publish;
import com.wj.pubsub.subscribe.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author jun.wang
 * @title: RedisPulish
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/10 9:56
 */

@Component
public class RedisPublish implements Publish {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void publish(Channel channel, Object message) {
        String channelName = channel.getChannelName();
        redisTemplate.convertAndSend(channelName, message);
    }
}
