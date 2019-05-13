package com.wj.pubsub.publish;

import com.wj.pubsub.subscribe.Channel;

/**
 * @author jun.wang
 * @title: Publish
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/10 9:54
 */
public interface Publish {
    void publish(Channel channel, Object message);
}
