package com.minuteCart.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ShedulerConfig {

 @Bean
 @Primary
 public ThreadPoolTaskScheduler customTaskScheduler(){
     ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
     scheduler.setPoolSize(5);
     scheduler.setThreadNamePrefix("custom-scheduler-");
     scheduler.initialize();
     return scheduler;
 }

}
