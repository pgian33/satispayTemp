package it.satispay.signature.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import it.satispay.signature.exception.SatispayAuthException;

public class ClientErrorHandler extends DefaultResponseErrorHandler  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		LOGGER.trace(">>hasError: response={}", response);
		boolean isError = false;
		int rawStatusCode = response.getRawStatusCode();
		LOGGER.info("--hasError: rawStatusCode={}", rawStatusCode);
		if (!(rawStatusCode > 199 && rawStatusCode < 400)) {
			isError = true;
		}
		LOGGER.trace("<<hasError: isError={}", isError);
		return isError;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		LOGGER.trace(">>handleError: response={}", response);
		if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
				String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
				LOGGER.info(">>handleError: httpBodyResponse={}", httpBodyResponse);
				throw new SatispayAuthException(response.getStatusCode(), httpBodyResponse);
			}
		}
	}
}
