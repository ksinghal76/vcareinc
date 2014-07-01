package com.vcareinc.models.amazonws;

import com.vcareinc.models.BaseModel;

@SuppressWarnings("rawtypes")
public class AmazonS3Config extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String accessKey;
	private String secretKey;
	private String bucketName;
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

}
