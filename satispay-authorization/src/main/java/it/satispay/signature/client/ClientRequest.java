package it.satispay.signature.client;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class ClientRequest {

	protected String endpoint;
	protected Map<String, String []> params;
	protected HttpMethod method;
	protected HttpHeaders headers = new HttpHeaders();
	protected String body;
	
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public Map<String, String[]> getParams() {
		return params;
	}
	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}
	public HttpMethod getMethod() {
		return method;
	}
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	public HttpHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
