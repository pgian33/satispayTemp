package it.satispay.signature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.satispay.signature.bean.AuthenticationResponse;
import it.satispay.signature.exception.SatispayAuthException;
import it.satispay.signature.service.SatispayAuthService;

@Component
@RestController
@RequestMapping(value = "/")
public class SatispayAuthController {

	@Autowired
	private SatispayAuthService satispayAuthService;
	private static final Logger LOGGER = LoggerFactory.getLogger(SatispayAuthController.class);

	@PostMapping(value = "/authorize-satispay")
	public ResponseEntity<?> postSatispayAuth(@RequestBody String body) {
		return getResponse(body, HttpMethod.POST);
	}

	@GetMapping(value = "/authorize-satispay")
	public ResponseEntity<?> getSatispayAuth() {
		return getResponse("", HttpMethod.GET);
	}

	@PutMapping(value = "/authorize-satispay")
	public ResponseEntity<?> putSatispayAuth(@RequestBody String body) {
		return getResponse("", HttpMethod.PUT);
	}

	@DeleteMapping(value = "/authorize-satispay")
	public ResponseEntity<?> deleteSatispayAuth() {
		return getResponse("", HttpMethod.DELETE);
	}

	private ResponseEntity<?> getResponse(String body, HttpMethod method) {
		AuthenticationResponse response = null;
		try {
			response = satispayAuthService.satispayAuth(body, method);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (SatispayAuthException e) {
			LOGGER.error("A SatispayAuthException occurred", e);
			return new ResponseEntity(e.getError(), e.getStatusCode());
		}
		catch (Exception e) {
			LOGGER.error("A generic Exception occcured", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
