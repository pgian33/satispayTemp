package it.satispay.signature.bean;

public class ErrorResponse {

	private Integer code;
	private String message;
	private String wlt;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWlt() {
		return wlt;
	}

	public void setWlt(String wlt) {
		this.wlt = wlt;
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + ", wlt=" + wlt + "]";
	}
}