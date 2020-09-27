package it.satispay.signature.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


@Component
public class AuthorizationHelper {

	@Value("${path.satispayAuthServicesEndpoint}")
	private String satispayAuthServicesEndpoint;
	@Value("${path.satispayAuthenticationRef}")
	private String satispayAuthenticationRef;
	@Value("${signature.keyId}")
	private String signatureKeyId;
	@Value("${signature.algorithm}")
	private String signatureAlgorithm;
	@Value("${signature.privateKeyFileName}")
	private String privateKeyFileName;
	@Value("${date.format}")
	private String dateFormat;



	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationHelper.class);

	public String generateDigest(String body) throws NoSuchAlgorithmException {
		LOGGER.trace(">> generateDigest: body={}", body);
		String digest = "";
		byte[] data = body.getBytes();
		MessageDigest digester = MessageDigest.getInstance("SHA-256");
		digester.update(data);
		digest = "SHA-256=" + Base64.getEncoder().encodeToString(digester.digest());
		LOGGER.trace("<< generateDigest: digest={}", digest);
		return digest;
	}

	public String getAuthorizationHeader(String ref, String host, String now, String digest, HttpMethod method) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException, InvalidKeyException, SignatureException {
		LOGGER.trace(">> getAuthorizationHeader: host={}, now={}, digest={}, method={}", host, now, digest, method);
		
		StringBuilder str = new StringBuilder();
		str.append("(request-target): ")
		.append(method.name().toLowerCase())
		.append(" ")
		.append(ref)
		.append("\ndate: ")
		.append(now)
		.append("\ndigest: ")
		.append(digest)
		.append("\nhost: ")
		.append(host);

		String signingString = str.toString();
		LOGGER.info("-- getAuthorizationHeader: signingString={}", signingString);
		PrivateKey privateKey = AuthorizationHelper.getPrivateKey(privateKeyFileName);
		String signature = AuthorizationHelper.sign(signingString, privateKey);

		str = new StringBuilder();
		str.append("Signature keyId=\"")
		.append(signatureKeyId)
		.append("\",algorithm=\"")
		.append(signatureAlgorithm)
		.append("\",headers=\"(request-target) date digest host\",signature=\"")
		.append(signature)
		.append("\"");

		String authorizationHeader = str.toString();
		LOGGER.trace(">> getAuthorizationHeader: authorizationHeader={}", authorizationHeader);
		return authorizationHeader;
	}

	public static PrivateKey getPrivateKey(String filename) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeySpecException  {
		LOGGER.trace(">> getPrivateKey: filename={}", filename);

		List<String> lines = Files.readAllLines(Paths.get(AuthorizationHelper.class.getClassLoader().getResource(filename).toURI()), StandardCharsets.US_ASCII);
		if (lines.size() < 2) {
			LOGGER.error("!! getPrivateKey: inadequate input file");
			throw new IllegalArgumentException("Insufficient input");
		}
		if (!lines.remove(0).startsWith("--")) {
			LOGGER.error("!! getPrivateKey: missing header");
			throw new IllegalArgumentException("Expected header");
		}
		if (!lines.remove(lines.size() - 1).startsWith("--")) { 
			LOGGER.error("!! getPrivateKey: missing footer");
			throw new IllegalArgumentException("Expected footer");
		}
		byte[] keyBytes = Base64.getDecoder().decode(String.join("", lines));

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public static String sign(String plainText, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		LOGGER.trace(">> sign: plainText={}, privateKey={}", plainText, plainText);
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(privateKey);
		privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

		byte[] signatureByte = privateSignature.sign();
		final String signature = Base64.getEncoder().encodeToString(signatureByte);
		LOGGER.trace("<< sign: sinature={}", signature);
		return signature;
	}	
}
