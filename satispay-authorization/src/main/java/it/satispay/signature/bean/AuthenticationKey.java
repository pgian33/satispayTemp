package it.satispay.signature.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthenticationKey {
	
	@JsonAlias({"access_key"})
	private String accessKey;
	
	@JsonAlias({"customer_uid"})
	private String customerUid;
	
	@JsonAlias({"key_type"})
	private String keyType;
	
	@JsonAlias({"auth_type"})
	private String authType;
	
	private String role;
	
	private String enable;
	
	@JsonAlias({"insert_date"})
	private String insertDate;
	
	@JsonAlias({"update_date"})
	private String updateDate;
	
	private String version;
	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getCustomerUid() {
		return customerUid;
	}
	public void setCustomerUid(String customerUid) {
		this.customerUid = customerUid;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "AuthenticationKey [accessKey=" + accessKey + ", customerUid=" + customerUid + ", keyType=" + keyType
				+ ", authType=" + authType + ", role=" + role + ", enable=" + enable + ", insertDate=" + insertDate
				+ ", updateDate=" + updateDate + ", version=" + version + "]";
	}
}
