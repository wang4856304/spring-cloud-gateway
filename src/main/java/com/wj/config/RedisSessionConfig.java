package com.wj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * @author jun.wang
 * @title: RedisSessionConfig
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/30 10:06
 */

@Configuration
@EnableRedisWebSession
public class RedisSessionConfig {
}
