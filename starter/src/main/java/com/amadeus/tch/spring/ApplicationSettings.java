package com.amadeus.tch.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tch", ignoreUnknownFields = false)
public class ApplicationSettings {

	private ServerSettings server;

	private AsyncSettings async;

	public ServerSettings getServer() {
		return server;
	}

	public void setServer(ServerSettings server) {
		this.server = server;
	}

	public AsyncSettings getAsync() {
		return async;
	}

	public void setAsync(AsyncSettings async) {
		this.async = async;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationSettings [server=");
		builder.append(server);
		builder.append(", async=");
		builder.append(async);
		builder.append("]");
		return builder.toString();
	}

	public static class ServerSettings {
		private int springWorkerThreads = 10;

		public int getSpringWorkerThreads() {
			return springWorkerThreads;
		}

		public void setSpringWorkerThreads(int springWorkerThreads) {
			this.springWorkerThreads = springWorkerThreads;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ServerSettings [springWorkerThreads=");
			builder.append(springWorkerThreads);
			builder.append("]");
			return builder.toString();
		}

	}

	public static class AsyncSettings {
		private int corePoolsize = 1;
		private int maxThreadPoolSize = 32;
		private int keepAliveSeconds = 60;

		public int getCorePoolsize() {
			return corePoolsize;
		}

		public void setCorePoolsize(int corePoolsize) {
			this.corePoolsize = corePoolsize;
		}

		public int getMaxThreadPoolSize() {
			return maxThreadPoolSize;
		}

		public void setMaxThreadPoolSize(int maxThreadPoolSize) {
			this.maxThreadPoolSize = maxThreadPoolSize;
		}

		public int getKeepAliveSeconds() {
			return keepAliveSeconds;
		}

		public void setKeepAliveSeconds(int keepAliveSeconds) {
			this.keepAliveSeconds = keepAliveSeconds;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AsyncSettings [corePoolsize=");
			builder.append(corePoolsize);
			builder.append(", maxThreadPoolSize=");
			builder.append(maxThreadPoolSize);
			builder.append(", keepAliveSeconds=");
			builder.append(keepAliveSeconds);
			builder.append("]");
			return builder.toString();
		}

	}

}
