package com.learning.reference.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AssyncConfiguration {
	
	@Value("${application.executor.corePoolSize}")
	private Integer corePoolSSize;
	
	@Value("${application.executor.maxPoolSize}")
	private Integer maxPoolSize;
	
	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("asyncTask-");
		return executor;
	}
}
