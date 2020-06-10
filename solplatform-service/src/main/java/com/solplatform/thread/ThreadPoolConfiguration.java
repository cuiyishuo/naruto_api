package com.solplatform.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * @author sol
 * @create 2020-06-04  10:31 上午
 */
@Configuration
public class ThreadPoolConfiguration {
    // 核心线程数
    @Value("${spring.threadpool.core-pool-size}")
    private int corePoolSize;
    // 最大线程数
    @Value("${spring.threadpool.max-pool-size}")
    private int maxPoolSize;
    // 缓冲队列大小
    @Value("${spring.threadpool.queue-capacity}")
    private int queueCapacity;
    // 线程池名前缀
    @Value("${spring.threadpool.name-predix}")
    private String threadNamePrefix;

    /**
     * 执行测试队列线程
     *
     * @return
     */
    @Bean(value = "runTestThreadPool")
    public ThreadPoolTaskExecutor runTestThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor ();
        executor.setCorePoolSize (corePoolSize);
        executor.setMaxPoolSize (maxPoolSize);
        executor.setQueueCapacity (queueCapacity);
        executor.setThreadNamePrefix (threadNamePrefix);
        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}
