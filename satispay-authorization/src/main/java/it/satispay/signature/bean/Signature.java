package it.satispay.signature.bean;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Signature {
	
	@JsonAlias({"key_id"})
	private String keyId;
	
	private String algorithm;
	
	private String [] headers;
	
	@JsonAlias({"signature"})
	private String signatureF;
	
	@JsonAlias({"resign_required"})
	private String resignRequired;
	
	@JsonAlias({"iteration_count"})
	private String iterationCount;
	
	private String valid;
	
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public String getSignatureF() {
		return signatureF;
	}
	public void setSignatureF(String signatureF) {
		this.signatureF = signatureF;
	}
	public String getResignRequired() {
		return resignRequired;
	}
	public void setResignRequired(String resignRequired) {
		this.resignRequired = resignRequired;
	}
	public String getIterationCount() {
		return iterationCount;
	}
	public void setIterationCount(String iterationCount) {
		this.iterationCount = iterationCount;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "Signature [keyId=" + keyId + ", algorithm=" + algorithm + ", headers=" + Arrays.toString(headers)
				+ ", signatureF=" + signatureF + ", resignRequired=" + resignRequired + ", iterationCount="
				+ iterationCount + ", valid=" + valid + "]";
	}
}
