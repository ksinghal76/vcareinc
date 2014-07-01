package com.vcareinc.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.vcareinc.constants.UploadFileType;

@Entity
public class FileUpload implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String filename;
	private UploadFileType uploadType;
	private String clientFilename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public UploadFileType getUploadType() {
		return uploadType;
	}

	public void setUploadType(UploadFileType uploadType) {
		this.uploadType = uploadType;
	}

	public String getClientFilename() {
		return clientFilename;
	}

	public void setClientFilename(String clientFilename) {
		this.clientFilename = clientFilename;
	}

}
