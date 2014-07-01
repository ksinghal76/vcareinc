package com.vcareinc.constants;

public enum ContentType {
	IMAGE_JPEG("image/jpeg"),
	IMAGE_GIF("image/gif"),
	IMAGE_PNG("image/png"),
	APPLICATION_PDF("application/pdf"),
	APPLICATION_MSWORD("application/msword"),
	TEXT_PLAIN("text/plain");


	private String name;

	ContentType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ContentType getContentType(String contentType) {
		ContentType valContType = null;
		for(ContentType contentTypes : ContentType.values()) {
			if(contentTypes.getName().equalsIgnoreCase(contentType)) {
				valContType = contentTypes;
				break;
			}
		}
		return valContType;
	}
}
