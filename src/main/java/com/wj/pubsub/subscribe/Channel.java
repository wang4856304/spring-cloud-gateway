package com.wj.pubsub.subscribe;

/**
 * @author jun.wang
 * @title: Channel
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/9 16:37
 */
public enum Channel {

    REFRESH_ROUTE("refresh_route_channel", "refreshRouteReceiver", "receiveMessage", "路由刷新");

    Channel(String channelName, String receiverBeanName, String methodName, String desc) {
        this.channelName = channelName;
        this.desc = desc;
        this.receiverBeanName = receiverBeanName;
        this.methodName = methodName;
    }

    private String channelName;
    private String receiverBeanName;
    private String methodName;
    private String desc;

    public String getChannelName() {
        return channelName;
    }

    public String getReceiverBeanName() {
        return receiverBeanName;
    }

    public String getMethodName() {
        return methodName;
    }
}
