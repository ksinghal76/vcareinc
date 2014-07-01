package com.vcareinc.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.constants.ContentType;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {

	String message;
	List<ContentType> contentTypes;
	long maxFileSize;
	String maxFileSizeMessage;

	@Override
	public void initialize(FileExtension param) {
		message = param.message();
		contentTypes = new ArrayList<ContentType>(Arrays.asList(param.contentTypeList()));
		maxFileSize = param.maxFileSize();
		maxFileSizeMessage = param.maxFileSizeMessage();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if(file == null || file.getSize() <= 0)
			return true;

		if(file.getSize() > maxFileSize) {
			ctx.buildConstraintViolationWithTemplate(maxFileSizeMessage.replace("{max}", String.valueOf(maxFileSize))).addConstraintViolation();
			return false;
		}

		if(contentTypes.contains(ContentType.getContentType(file.getContentType()))) {
			return true;
		} else {
			ctx.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}

		return false;
	}

}
