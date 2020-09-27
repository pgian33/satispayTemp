package it.satispay.signature.client;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Client {

	private static final Logger _LOG = LoggerFactory.getLogger(Client.class);

	@Autowired
	RestTemplate restTemplate;

	public <T> T performRequest(ClientRequest request, Class<T> responseClass) {
		_LOG.trace(">>performRequest: request={}, responseClass={}", request, responseClass);
		long timestamp = System.currentTimeMillis();
		HttpEntity<T> response = null;
		try {
			_LOG.info("--performRequest: Performing request to {}", request.getEndpoint());
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			HttpEntity<String> entity = new HttpEntity<>(request.getBody(), request.getHeaders());
			restTemplate.setErrorHandler(new ClientErrorHandler());
			response = restTemplate.exchange(request.getEndpoint(), request.getMethod(), entity, responseClass);
		}
		finally {
			_LOG.info("<<performRequest: responseTime={} ms", System.currentTimeMillis() - timestamp);
		}
		return response.getBody();
	}
}