package com.amadeus.tch.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tch", ignoreUnknownFields = false)
public class ApplicationSettings {

	private ServerSettings server;

	public ServerSettings getServer() {
		return server;
	}

	public void setServer(ServerSettings server) {
		this.server = server;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationSettings [server=").append(server).append("]");
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

}
