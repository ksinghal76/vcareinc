package com.vcareinc.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.vcareinc.constants.ContentType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy=FileExtensionValidator.class)
public @interface FileExtension {

	String message();

	ContentType[] contentTypeList();

	long maxFileSize() default 1000000;

	String maxFileSizeMessage() default "File Size oversized!!!";

	@SuppressWarnings("rawtypes")
	Class[] groups() default {};

	@SuppressWarnings("rawtypes")
	Class[] payload() default {};
}
