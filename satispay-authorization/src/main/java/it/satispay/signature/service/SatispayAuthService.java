package it.satispay.signature.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.satispay.signature.bean.AuthenticationResponse;
import it.satispay.signature.client.Client;
import it.satispay.signature.client.ClientRequest;
import it.satispay.signature.helper.AuthorizationHelper;

@Component
public class SatispayAuthService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SatispayAuthService.class);

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	AuthorizationHelper authorizationHelper;
	@Autowired
	Client client;

	@Value("${path.satispayAuthServicesEndpoint}")
	private String satispayAuthServicesEndpoint;
	@Value("${path.satispayAuthenticationRef}")
	private String satispayAuthenticationRef;
	@Value("${date.format}")
	private String dateFormat;

	public static final String REGEXP_PATTER_PROTOCOL = "^(http[s]?://www\\.|http[s]?://|www\\.)";

	public AuthenticationResponse satispayAuth(String body, HttpMethod method) {
		LOGGER.trace(">> satispayAuth: body={}, method={}", body, method);

		ClientRequest clientRequest = new ClientRequest();
		clientRequest.setBody(body);
		clientRequest.setEndpoint(satispayAuthServicesEndpoint.concat(satispayAuthenticationRef));

		clientRequest.setMethod(method);

		AuthenticationResponse authenticationResponse = null;
		try {
			String digest = authorizationHelper.generateDigest(body);
			HttpHeaders headers = new HttpHeaders();
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			final String now = simpleDateFormat.format(new Date());
			final String normalizedEndpoint = satispayAuthServicesEndpoint.replaceFirst(REGEXP_PATTER_PROTOCOL,"");

			String authorizationHeader = authorizationHelper.getAuthorizationHeader(satispayAuthenticationRef, normalizedEndpoint, now, digest, method);
			headers.add("Host", satispayAuthServicesEndpoint);
			headers.add("Content-Type", "application/json");
			headers.add("digest", digest);
			headers.add("Date", now);
			headers.add("Authorization", authorizationHeader);
			clientRequest.setHeaders(headers);
			authenticationResponse = client.performRequest(clientRequest, AuthenticationResponse.class);
		}
		catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | SignatureException | IOException | URISyntaxException e) {
			LOGGER.error("!! An exception occurred during authentication", e);
		}
		LOGGER.trace("<< satispayAuth: authenticationResponse={}", authenticationResponse);
		return authenticationResponse;
	}
}
