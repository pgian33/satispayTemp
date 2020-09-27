package it.satispay.signature.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	@Value("${client.connectionTimeout}")
	private Long connectionTimeout;
	@Value("${client.readTimeout}")
	private Long readTimeout;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder.setConnectTimeout(Duration.ofMillis(connectionTimeout))
	     .setReadTimeout(Duration.ofMillis(readTimeout)).build();
	}
}
