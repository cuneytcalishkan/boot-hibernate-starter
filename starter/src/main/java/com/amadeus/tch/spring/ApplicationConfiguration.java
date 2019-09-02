package com.amadeus.tch.spring;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.core.instrument.util.NamedThreadFactory;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

	private final ApplicationSettings settings;

	@Autowired
	public ApplicationConfiguration(ApplicationSettings stngs) {
		this.settings = stngs;
		logger.info(settings.toString());
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(new ConcurrentTaskExecutor(sharedTaskExecutor()));
	}
	
	  private ExecutorService sharedTaskExecutor() {

		    ThreadFactory threadFactory = new NamedThreadFactory("spring-worker-");
		    
		    ExecutorService svc = new ThreadPoolExecutor(
		        settings.getServer().getSpringWorkerThreads(),
		        settings.getServer().getSpringWorkerThreads(),
		        10, TimeUnit.MILLISECONDS,
		        new SynchronousQueue<Runnable>(),
		        threadFactory);

		    return svc;
		  }

}
