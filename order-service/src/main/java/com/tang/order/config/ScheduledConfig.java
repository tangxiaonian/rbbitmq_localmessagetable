package com.tang.order.config;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * @Classname ScheduledConfig
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/12 8:51
 * @Created by ASUS
 */
@Component
public class ScheduledConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        // 配置多线程模式
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));

    }
}