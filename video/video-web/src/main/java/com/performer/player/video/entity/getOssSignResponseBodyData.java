package com.performer.player.video.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author linyunzhe
 *
 */
public class getOssSignResponseBodyData {

	private static final String ACCESS_ID = "accessid";
	private static final String POLICY = "policy";
	private static final String SIGNATURE = "signature";
	private static final String DIR = "dir";
	private static final String HOST = "host";
	private static final String EXPIRE = "expire";
	private static final String CALL_BACK = "callback";
	/**
	 * OSSAccessKeyId
	 */
	@JsonProperty(ACCESS_ID)
	private String accessid;
	/**
	 * policy规定了该次Post请求中表单域的合法�?�，OSS会根据policy判断请求的合法�??
	 */
	@JsonProperty(POLICY)
	private String policy;
	/**
	 * 根据Access Key Secret和policy计算的签名信息，OSS验证该签名信息从而验证该Post请求的合法�??
	 */
	@JsonProperty(SIGNATURE)
	private String signature;
	/**
	 * 存储的文件对�?
	 */
	@JsonProperty(DIR)
	private String dir;
	/**
	 * 上传的地�?
	 */
	@JsonProperty(HOST)
	private String host;
	/**
	 * policy的过期时�?
	 */
	@JsonProperty(EXPIRE)
	private String expire;
	/**
	 * 回调内容
	 */
	@JsonProperty(CALL_BACK)
	private String callback;
	
	public String getAccessid() {
		return accessid;
	}
	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
}
