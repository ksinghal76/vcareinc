package com.vcareinc.services.amazonws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

@Controller
public class AmazonS3Service {

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Autowired
	private String bucketName;

	public Boolean uploadFileByMultipartFile(MultipartFile file, String filename) {
		try {
			InputStream stream = new ByteArrayInputStream(file.getBytes());
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.getSize());
			meta.setContentType(file.getContentType());
			amazonS3Client.putObject(bucketName, filename, stream, meta);
			amazonS3Client.setObjectAcl(bucketName, filename, CannedAccessControlList.PublicRead);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public InputStream getObjectDataByKey(String key) {
		InputStream stream = null;

		GetObjectRequest objectRequest = new GetObjectRequest(bucketName, key);
		objectRequest.setRange(0, 10);
		S3Object objectPortion = amazonS3Client.getObject(objectRequest);
		stream = objectPortion.getObjectContent();
		return stream;
	}

	public AmazonS3Client getAmazonS3Client() {
		return amazonS3Client;
	}

	public void setAmazonS3Client(AmazonS3Client amazonS3Client) {
		this.amazonS3Client = amazonS3Client;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
}
