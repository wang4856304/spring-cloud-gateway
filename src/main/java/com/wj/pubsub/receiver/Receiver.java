package com.wj.pubsub.receiver;

/**
 * @author jun.wang
 * @title: RedisReceiver
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/9 16:27
 */
public interface Receiver {

    void receiveMessage(String message);
}
