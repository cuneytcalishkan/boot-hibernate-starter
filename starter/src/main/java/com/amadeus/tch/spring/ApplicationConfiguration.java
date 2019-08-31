package com.amadeus.tch.spring;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ApplicationConfiguration implements AsyncConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

	private final ApplicationSettings settings;

	@Autowired
	public ApplicationConfiguration(ApplicationSettings stngs) {
		this.settings = stngs;
		logger.info(settings.toString());
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadFactory threadFactory = new CustomizableThreadFactory("async-worker-");
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setThreadFactory(threadFactory);
		taskExecutor.setCorePoolSize(settings.getAsync().getCorePoolsize());
		taskExecutor.setMaxPoolSize(settings.getAsync().getMaxThreadPoolSize());
		taskExecutor.setKeepAliveSeconds(settings.getAsync().getKeepAliveSeconds());
		taskExecutor.initialize();
		return taskExecutor;
	}

}
