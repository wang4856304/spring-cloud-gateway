package com.wj.pubsub.subscribe;

import com.wj.pubsub.receiver.Receiver;
import com.wj.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author jun.wang
 * @title: RedisListenerConfig
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/9 16:26
 */

@Configuration
public class RedisListenerConfig {


    private static Logger logger = LoggerFactory.getLogger(RedisListenerConfig.class);

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //可以添加多个 messageListener
        Channel[] arr = Channel.values();
        for (Channel channel: arr) {
            String channelName = channel.getChannelName();
            String receiverBeanName = channel.getReceiverBeanName();
            Receiver receiver = SpringContextUtil.getBean(receiverBeanName, Receiver.class);
            if (null == receiver) {
                logger.error("bean is empty, redisReceiver is {}", receiverBeanName);
                throw new RuntimeException(String.format("bean is empty, redisReceiver is %s", receiverBeanName));
            }
            String methodName = channel.getMethodName();
            MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, methodName);
            adapter.afterPropertiesSet();
            container.addMessageListener(adapter, new PatternTopic(channelName));
        }
        return container;
    }
}
