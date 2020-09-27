package it.satispay.signature.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {

	@JsonAlias({"authentication_key"})
	private AuthenticationKey authenticationKey;
	
	private Signature signature;
	
	@JsonAlias({"signed_string"})
	private String signedString;
	
	public AuthenticationKey getAuthenticationKey() {
		return authenticationKey;
	}
	public void setAuthenticationKey(AuthenticationKey authenticationKey) {
		this.authenticationKey = authenticationKey;
	}
	public Signature getSignature() {
		return signature;
	}
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	public String getSignedString() {
		return signedString;
	}
	public void setSignedString(String signedString) {
		this.signedString = signedString;
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [authenticationKey=" + authenticationKey.toString() + ", signature=" + signature.toString()
				+ ", signedString=" + signedString + "]";
	}
	
	
}
