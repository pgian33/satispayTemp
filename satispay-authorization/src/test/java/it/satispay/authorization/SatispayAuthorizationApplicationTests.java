package it.satispay.authorization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.satispay.signature.bean.AuthenticationResponse;
import it.satispay.signature.controller.SatispayAuthController;
import net.minidev.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
class SatispayAuthorizationApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SatispayAuthorizationApplicationTests.class);

	
	private String jsonTest_1 = "";
	private String jsonTest_2 = "";
	
    @BeforeEach
    public void init() {
    	LOGGER.info(">> init: Creating input test");
        JSONObject json= new JSONObject();
        json.put("dinosaur", "Tyrannosaurs Rex");
        jsonTest_1 = json.toJSONString();
        json.put("chess", "Rook");
        json.put("surf", "Take off");
        jsonTest_2 = json.toJSONString();
    }
	
	@Autowired
	private SatispayAuthController satispayAuthController;
	
	@Test
	void contextLoads() throws Exception {
		LOGGER.info(">> contextLoads: Checking satispayAuthController is not null ");
		assertThat(satispayAuthController).isNotNull();
	}
	
	@Test
	void getAuth200() throws Exception {
		LOGGER.info(">> getAuth200: Checking GET response is 200 OK");
		ResponseEntity<?> caa = satispayAuthController.getSatispayAuth();
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void getSignAuthCheckRole() throws Exception {
		LOGGER.info(">> getSignAuthCheckRole: Checking Authorization role is ONLINE_SHOR or DEVICE");
		ResponseEntity<?> caa = satispayAuthController.getSatispayAuth();
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
	
	@Test
	void postAuth200_1() throws Exception {
		LOGGER.info(">> postAuth200_1: Checking POST response is 200 OK with payload={}", jsonTest_1);
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth(jsonTest_1);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void postAuth200_2() throws Exception {
		LOGGER.info(">> postAuth200_2: Checking POST response is 200 OK with payload={}", jsonTest_2);
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth(jsonTest_2);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void postAuth200_3() throws Exception {
		LOGGER.info(">> postAuth200_2: Checking POST response is 200 OK with empty payload");
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth("");
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void postSignAuthCheckRole_1() throws Exception {
		LOGGER.info(">> postSignAuthCheckRole_1: Checking Authorization role is ONLINE_SHOR or DEVICE with payload={}", jsonTest_1);
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth(jsonTest_1);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
	
	@Test
	void postSignAuthCheckRole_2() throws Exception {
		LOGGER.info(">> postSignAuthCheckRole_2: Checking Authorization role is ONLINE_SHOR or DEVICE with payload={}", jsonTest_2);
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth(jsonTest_2);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
	
	void postSignAuthCheckRole_3() throws Exception {
		LOGGER.info(">> postSignAuthCheckRole_2: Checking Authorization role is ONLINE_SHOR or DEVICE with empty payload");
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth("");
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
	
	@Test
	void putAuth200_1() throws Exception {
		LOGGER.info(">> putAuth200_1: Checking PUT response is 200 OK with payload={}", jsonTest_1);
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth(jsonTest_1);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void putAuth200_2() throws Exception {
		LOGGER.info(">> putAuth200_2: Checking PUT response is 200 OK with payload={}", jsonTest_2);
		ResponseEntity<?> caa = satispayAuthController.postSatispayAuth(jsonTest_2);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void putSignAuthCheckRole_1() throws Exception {
		LOGGER.info(">> putSignAuthCheckRole_1: Checking Authorization role is ONLINE_SHOR or DEVICE with payload={}", jsonTest_1);
		ResponseEntity<?> caa = satispayAuthController.putSatispayAuth(jsonTest_1);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
	
	@Test
	void putSignAuthCheckRole_2() throws Exception {
		LOGGER.info(">> putSignAuthCheckRole_2: Checking Authorization role is ONLINE_SHOR or DEVICE with payload={}", jsonTest_2);
		ResponseEntity<?> caa = satispayAuthController.putSatispayAuth(jsonTest_2);
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
	
	@Test
	void deleteAuth200() throws Exception {
		LOGGER.info(">> deleteAuth200: Checking DELETE response is 200 OK");
		ResponseEntity<?> caa = satispayAuthController.deleteSatispayAuth();
		assertEquals(HttpStatus.OK, caa.getStatusCode());
	}
	
	@Test
	void deletSignAuthCheckRole() throws Exception {
		LOGGER.info(">> deletSignAuthCheckRole: Checking Authorization role is ONLINE_SHOR or DEVICE");
		ResponseEntity<?> caa = satispayAuthController.deleteSatispayAuth();
		assertEquals(HttpStatus.OK, caa.getStatusCode());
		AuthenticationResponse authResponse = (AuthenticationResponse) caa.getBody();
		assertThat(authResponse).isNotNull();
		assertTrue(authResponse.getAuthenticationKey().getRole().matches("(ONLINE_SHOP|DEVICE)"));
	}
}
